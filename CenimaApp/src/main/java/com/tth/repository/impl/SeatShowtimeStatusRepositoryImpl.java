/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

/**
 *
 * @author Administrator
 */
import com.tth.pojo.SeatShowtimeStatus;
import com.tth.repository.SeatShowtimeStatusRepository;
import jakarta.persistence.LockModeType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SeatShowtimeStatusRepositoryImpl
        implements SeatShowtimeStatusRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public SeatShowtimeStatus find(
            Integer showtimeId,
            Integer seatId) {

        String hql = """
            FROM SeatShowtimeStatus s
            WHERE s.showtimeId.id=:showtimeId
            AND s.seatId.id=:seatId
        """;

        return factory
                .getCurrentSession()
                .createQuery(
                        hql,
                        SeatShowtimeStatus.class)
                .setParameter(
                        "showtimeId",
                        showtimeId)
                .setParameter(
                        "seatId",
                        seatId)
                .uniqueResult();
    }

    @Override
    public void save(
            SeatShowtimeStatus status) {

        Session s
                = factory.getCurrentSession();

        if (status.getId() == null) {
            s.persist(status);
        } else {
            s.merge(status);
        }
    }

    @Override
    public List<SeatShowtimeStatus>
            getByShowtime(
                    Integer showtimeId) {

        return factory
                .getCurrentSession()
                .createQuery(
                        """
                        FROM SeatShowtimeStatus s
                        WHERE s.showtimeId.id=:id
                        """,
                        SeatShowtimeStatus.class)
                .setParameter(
                        "id",
                        showtimeId)
                .getResultList();
    }

    @Override
    public boolean isBooked(
            Integer showtimeId,
            Integer seatId) {

        SeatShowtimeStatus s
                = find(showtimeId, seatId);

        return s != null
                && "BOOKED".equals(
                        s.getStatus());
    }

    @Override
    public boolean isLocked(
            Integer showtimeId,
            Integer seatId) {

        SeatShowtimeStatus s
                = find(showtimeId, seatId);

        return s != null
                && "LOCKED".equals(
                        s.getStatus());
    }

    @Override
    public void releaseExpiredLocks() {

        Session session
                = factory.getCurrentSession();

        Date now = new Date();

        Calendar cal
                = Calendar.getInstance();

        cal.setTime(now);

        cal.add(Calendar.MINUTE, -5);

        Date expiredTime
                = cal.getTime();

        session.createMutationQuery("""
        UPDATE SeatShowtimeStatus s
        SET s.status='AVAILABLE',
            s.userId=NULL,
            s.lockTime=NULL
        WHERE s.status='LOCKED'
        AND s.lockTime < :expiredTime
    """)
                .setParameter(
                        "expiredTime",
                        expiredTime)
                .executeUpdate();
    }

    @Override
    public SeatShowtimeStatus
            findForUpdate(
                    Integer showtimeId,
                    Integer seatId) {

        Session session
                = this.factory.getCurrentSession();

        return session.createQuery(
                """
            FROM SeatShowtimeStatus s
            WHERE s.showtimeId.id=:showtimeId
            AND s.seatId.id=:seatId
            """,
                SeatShowtimeStatus.class)
                .setParameter(
                        "showtimeId",
                        showtimeId)
                .setParameter(
                        "seatId",
                        seatId)
                .setLockMode(
                        LockModeType.PESSIMISTIC_WRITE)
                .uniqueResult();
    }
}
