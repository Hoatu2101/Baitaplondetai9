/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */


import com.tth.pojo.Bookings;
import java.util.List;

public interface BookingService {

    Bookings bookTicket(
            Integer showtimeId,
            Integer seatId,
            String username);

    List<Bookings> getMyBookings(
            String username);

    boolean isSeatBooked(
            Integer showtimeId,
            Integer seatId);
}