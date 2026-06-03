/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */
import com.tth.pojo.Tickets;
import java.util.List;

public interface TicketService {

    Tickets getTicketById(Integer id);

    List<Tickets> getTicketsByBooking(Integer bookingId);

    long countTicketsByShowtime(Integer showtimeId);
}