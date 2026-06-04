/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

/**
 *
 * @author Admin
 */
import com.tth.pojo.Tickets;
import com.tth.repository.TicketRepository;
import com.tth.service.TicketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketServiceImpl
        implements TicketService {

    @Autowired
    private TicketRepository ticketRepo;

    @Override
    public Tickets getTicketById(Integer id) {
        return ticketRepo.getTicketById(id);
    }

    @Override
    public List<Tickets> getTicketsByBooking(
            Integer bookingId) {

        return ticketRepo
                .getTicketsByBooking(
                        bookingId);
    }

    @Override
    public long countTicketsByShowtime(
            Integer showtimeId) {

        return ticketRepo
                .countTicketsByShowtime(
                        showtimeId);
    }
}