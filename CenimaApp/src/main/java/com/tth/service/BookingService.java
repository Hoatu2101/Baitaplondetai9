/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */


import com.tth.dto.BookingDetailResponse;
import com.tth.dto.ShowtimeStatisticResponse;
import com.tth.pojo.Bookings;
import java.util.Date;
import java.util.List;

public interface BookingService {

    Bookings bookTickets(
            Integer showtimeId,
            List<Integer> seatId,
            String username);

    List<Bookings> getMyBookings(String username);

    
    ShowtimeStatisticResponse getShowtimeStatistic(Integer showtimeId);

    List<BookingDetailResponse> getBookingDetailsByShowtime(Integer showtimeId);

    List<Bookings> getBookingsByDate(Date date);
}