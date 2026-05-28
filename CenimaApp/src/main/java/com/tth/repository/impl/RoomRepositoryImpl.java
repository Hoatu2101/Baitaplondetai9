/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tth.repository.impl;

import com.tth.pojo.Rooms;
import com.tth.repository.RoomRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Admin
 */

@Repository
@Transactional
public class RoomRepositoryImpl implements RoomRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Rooms> getRooms(Map<String, String> params) {

        Session s = this.factory.getCurrentSession();

        HibernateCriteriaBuilder cb = s.getCriteriaBuilder();

        var cq = cb.createQuery(Rooms.class);

        var root = cq.from(Rooms.class);

        cq.select(root);

        List predicates = new ArrayList<>();

        if (params != null) {

            String kw = params.get("kw");

            if (kw != null && !kw.isEmpty()) {

                predicates.add(
                        cb.like(
                                root.get("name"),
                                String.format("%%%s%%", kw)
                        )
                );
            }

            if (!predicates.isEmpty())
                cq.where((Predicate[]) predicates.toArray(Predicate[]::new));
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
    public Rooms getRoomById(int id) {

        Session s = this.factory.getCurrentSession();

        return s.get(Rooms.class, id);
    }

    @Override
    public void addOrUpdate(Rooms room) {

        Session s = this.factory.getCurrentSession();

        if (room.getId() == null)
            s.persist(room);
        else
            s.merge(room);
    }

    @Override
    public void deleteRoom(int id) {

        Session s = this.factory.getCurrentSession();

        Rooms room = this.getRoomById(id);

        s.remove(room);
    }

    @Override
    public long countRooms() {

        Session s = this.factory.getCurrentSession();

        return s.createQuery(
                "SELECT COUNT(*) FROM Rooms",
                Long.class
        ).getSingleResult();
    }
}