/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

/**
 *
 * @author Admin
 */
import com.tth.pojo.Showtimes;
import com.tth.repository.ShowtimeRepository;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ShowtimeRepositoryImpl implements ShowtimeRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Showtimes> getShowtimes(Map<String, String> params) {

        Session s = this.factory.getCurrentSession();

        HibernateCriteriaBuilder cb = s.getCriteriaBuilder();

        var cq = cb.createQuery(Showtimes.class);

        var root = cq.from(Showtimes.class);

        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {

            String kw = params.get("kw");

            if (kw != null && !kw.isEmpty()) {

                predicates.add(
                        cb.like(
                                root.get("movieId").get("movieName"),
                                String.format("%%%s%%", kw)
                        )
                );
            }
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(Predicate[]::new));
        }

        cq.orderBy(cb.desc(root.get("id")));

        Query query = s.createQuery(cq);

        if (params != null) {

            String page = params.get("page");

            int PAGE_SIZE = 10;

            if (page != null) {

                int p = Integer.parseInt(page);

                int start = (p - 1) * PAGE_SIZE;

                query.setFirstResult(start);

                query.setMaxResults(PAGE_SIZE);
            }
        }

        return query.getResultList();
    }

    @Override
    public Showtimes getShowtimeById(int id) {

        Session s = this.factory.getCurrentSession();

        return s.get(Showtimes.class, id);
    }

    @Override
    public void addOrUpdate(Showtimes showtime) {

        Session s = this.factory.getCurrentSession();

        if (showtime.getId() == null) {
            s.persist(showtime);
        } else {
            s.merge(showtime);
        }
    }

    @Override
    public void deleteShowtime(int id) {

        Session s = this.factory.getCurrentSession();

        Showtimes st = this.getShowtimeById(id);

        s.remove(st);
    }

    @Override
    public long countAvailableSeats(
            int showtimeId) {

        Session s
                = factory.getCurrentSession();

        String hql = """
        SELECT COUNT(se.id)
        FROM Seats se
        WHERE se.roomId.id =
        (
            SELECT st.roomId.id
            FROM Showtimes st
            WHERE st.id=:showtimeId
        )
        AND se.id NOT IN
        (
            SELECT sts.seatId.id
            FROM SeatShowtimeStatus sts
            WHERE sts.showtimeId.id=:showtimeId
            AND sts.status='BOOKED'
        )
    """;

        Long result
                = s.createQuery(
                        hql,
                        Long.class)
                        .setParameter(
                                "showtimeId",
                                showtimeId)
                        .uniqueResult();

        return result == null
                ? 0
                : result;
    }

    @Override
    public boolean isRoomBusy(
            int roomId,
            Date start,
            Date end,
            Integer showtimeId) {

        Session s = this.factory.getCurrentSession();

        String hql = """
            SELECT COUNT(s)
            FROM Showtimes s
            WHERE s.roomId.id = :roomId
            AND (
                (:start BETWEEN s.startTime AND s.endTime)
                OR
                (:end BETWEEN s.startTime AND s.endTime)
                OR
                (s.startTime BETWEEN :start AND :end)
            )
        """;

        if (showtimeId != null) {
            hql += " AND s.id <> :showtimeId";
        }

        var query = s.createQuery(hql, Long.class);

        query.setParameter("roomId", roomId);
        query.setParameter("start", start);
        query.setParameter("end", end);

        if (showtimeId != null) {
            query.setParameter("showtimeId", showtimeId);
        }

        Long count = query.getSingleResult();

        return count > 0;
    }
}
