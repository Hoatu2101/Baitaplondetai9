/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

/**
 *
 * @author Administrator
 */
import com.tth.pojo.Bookings;
import com.tth.repository.BookingRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BookingRepositoryImpl
        implements BookingRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public Bookings createBooking(
            Bookings booking) {

        Session s
                = factory.getCurrentSession();

        s.persist(booking);

        return booking;
    }


    @Override
    public List<Bookings> getBookingsByUser(
            String username) {

        Session s
                = factory.getCurrentSession();

        return s.createQuery(
                """
                FROM Bookings b
                WHERE b.userId.username=:username
                ORDER BY b.createdAt DESC
                """,
                Bookings.class)
                .setParameter(
                        "username",
                        username)
                .getResultList();
    }


    @Override
    public long countBookingByShowtime(
            Integer showtimeId) {

        Session s
                = factory.getCurrentSession();

        return s.createQuery(
                """
                SELECT COUNT(b)
                FROM Bookings b
                WHERE b.showtimeId.id=:id
                """,
                Long.class)
                .setParameter("id",
                        showtimeId)
                .getSingleResult();
    }
}
