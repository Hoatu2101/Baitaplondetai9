/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository;

import com.tth.pojo.Bookings;
import java.util.List;

/**
 *
 * @author Admin
 */

public interface BookingRepository {

    Bookings createBooking(Bookings booking);

    boolean isSeatBooked(
            Integer showtimeId,
            Integer seatId);

    List<Bookings> getBookingsByUser(
            String username);

    long countBookingByShowtime(
            Integer showtimeId);
}