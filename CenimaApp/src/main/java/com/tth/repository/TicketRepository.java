package com.tth.repository;

import com.tth.pojo.Tickets;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public interface TicketRepository {

    void createTicket(Tickets ticket);

    List<Tickets> getTicketsByBooking(
            Integer bookingId);
    

}