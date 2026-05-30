/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

/**
 *
 * @author Administrator
 */
import com.tth.pojo.Bookings;
import com.tth.pojo.Seats;
import com.tth.pojo.Showtimes;
import com.tth.pojo.Tickets;
import com.tth.pojo.Users;
import com.tth.repository.BookingRepository;
import com.tth.repository.DashboardRepository;
import com.tth.repository.SeatRepository;
import com.tth.repository.TicketRepository;
import com.tth.service.BookingService;
import com.tth.service.DashboardService;
import com.tth.service.ShowtimeService;
import com.tth.service.UserService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingServiceImpl
        implements BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private TicketRepository ticketRepo;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private UserService userService;

    @Autowired
    private SeatRepository seatRepo;

    @Override
    public Bookings bookTicket(
            Integer showtimeId,
            Integer seatId,
            String username) {

        if (bookingRepo.isSeatBooked(
                showtimeId,
                seatId)) {

            throw new RuntimeException(
                    "Ghế đã được đặt");
        }

        Showtimes showtime
                = showtimeService
                        .getShowtimeById(
                                showtimeId);

        Seats seat
                = seatRepo.getSeatById(
                        seatId);

        Users user
                = userService
                        .getUserByUsername(
                                username);

        float price
                = showtime
                        .getMovieId()
                        .getPrice();

        Bookings booking
                = new Bookings();

        booking.setCreatedAt(
                new Date());

        booking.setSeatId(seat);

        booking.setShowtimeId(
                showtime);

        booking.setUserId(user);

        booking.setTotalPrice(
                price);

        bookingRepo.createBooking(
                booking);

        Tickets ticket
                = new Tickets();

        ticket.setBookingId(
                booking);

        ticket.setSeatId(
                seat);

        ticket.setPrice(
                price);

        ticket.setCreatedAt(
                new Date());

        ticketRepo.createTicket(
                ticket);

        return booking;
    }

    @Override
    public List<Bookings> getMyBookings(
            String username) {

        return bookingRepo
                .getBookingsByUser(
                        username);
    }

    @Override
    public boolean isSeatBooked(
            Integer showtimeId,
            Integer seatId) {

        return bookingRepo
                .isSeatBooked(
                        showtimeId,
                        seatId);
    }
}
