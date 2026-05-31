/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

/**
 *
 * @author Administrator
 */


import com.tth.pojo.Seats;
import com.tth.repository.SeatRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SeatRepositoryImpl implements SeatRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Seats getSeatById(int id) {

        Session s = factory.getObject().getCurrentSession();

        return s.get(Seats.class, id);
    }

    @Override
    public List<Seats> getSeatsByRoom(int roomId) {

        Session s = factory.getObject().getCurrentSession();

        Query<Seats> q = s.createQuery(
                "FROM Seats s WHERE s.roomId.id=:roomId",
                Seats.class);

        q.setParameter("roomId", roomId);

        return q.getResultList();
    }


}