/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;


import com.tth.pojo.Tickets;
import com.tth.repository.TicketRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Repository
@Transactional
public class TicketRepositoryImpl
        implements TicketRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public void createTicket(
            Tickets ticket) {

        factory.getCurrentSession()
                .persist(ticket);
    }

    @Override
    public List<Tickets> getTicketsByBooking(
            Integer bookingId) {

        return factory
                .getCurrentSession()
                .createQuery(
                        """
                        FROM Tickets t
                        WHERE t.bookingId.id=:id
                        """,
                        Tickets.class)
                .setParameter("id",
                        bookingId)
                .getResultList();
    }
}