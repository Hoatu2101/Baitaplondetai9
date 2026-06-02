/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

/**
 *
 * @author Administrator
 */
import com.tth.dto.BookingDetailResponse;
import com.tth.dto.ShowtimeStatisticResponse;
import com.tth.pojo.Bookings;
import com.tth.pojo.SeatShowtimeStatus;
import com.tth.pojo.SeatStatus;
import com.tth.pojo.Seats;
import com.tth.pojo.Showtimes;
import com.tth.pojo.Tickets;
import com.tth.pojo.Users;
import com.tth.repository.BookingRepository;
import com.tth.repository.DashboardRepository;
import com.tth.repository.SeatRepository;
import com.tth.repository.SeatShowtimeStatusRepository;
import com.tth.repository.TicketRepository;
import com.tth.service.BookingService;
import com.tth.service.DashboardService;
import com.tth.service.ShowtimeService;
import com.tth.service.UserService;
import java.util.ArrayList;
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

    @Autowired
    private SeatShowtimeStatusRepository seatStatusRepo;

    @Override
    public Bookings bookTickets(
            Integer showtimeId,
            List<Integer> seatIds,
            String username) {

        Showtimes showtime
                = showtimeService
                        .getShowtimeById(
                                showtimeId);

        Users user
                = userService
                        .getUserByUsername(
                                username);

        float moviePrice
                = showtime
                        .getMovieId()
                        .getPrice();

        Bookings booking
                = new Bookings();

        booking.setCreatedAt(
                new Date());

        booking.setShowtimeId(
                showtime);

        booking.setUserId(user);

        booking.setTotalPrice(
                moviePrice
                * seatIds.size());

        bookingRepo.createBooking(
                booking);

        for (Integer seatId
                : seatIds) {

            SeatShowtimeStatus status
                    = seatStatusRepo.findForUpdate(
                            showtimeId,
                            seatId);

            if (status == null) {
                throw new RuntimeException(
                        "Ghế chưa được khóa");
            }

            if (!"LOCKED".equals(
                    status.getStatus())) {

                throw new RuntimeException(
                        "Ghế không ở trạng thái LOCKED");
            }

            if (status.getUserId() == null
                    || !status.getUserId()
                            .getUsername()
                            .equals(username)) {

                throw new RuntimeException(
                        "Ghế được khóa bởi người khác");
            }

            Seats seat
                    = seatRepo
                            .getSeatById(
                                    seatId);

            Tickets ticket
                    = new Tickets();

            ticket.setBookingId(
                    booking);

            ticket.setSeatId(
                    seat);

            ticket.setPrice(
                    moviePrice);

            ticket.setCreatedAt(
                    new Date());

            ticketRepo.createTicket(
                    ticket);

            status.setStatus(SeatStatus.BOOKED.name());

            seatStatusRepo.save(
                    status);
        }

        return booking;
    }

    @Override
    public List<Bookings>
            getMyBookings(
                    String username) {

        return bookingRepo
                .getBookingsByUser(
                        username);
    }

    @Override
    public ShowtimeStatisticResponse
            getShowtimeStatistic(
                    Integer showtimeId) {

        Long sold
                = ticketRepo
                        .countTicketsByShowtime(
                                showtimeId);

        Double revenue
                = bookingRepo
                        .revenueByShowtime(
                                showtimeId);

        return new ShowtimeStatisticResponse(
                showtimeId,
                sold,
                revenue);
    }

    @Override
    public List<BookingDetailResponse>
            getBookingDetailsByShowtime(
                    Integer showtimeId) {

        List<Bookings> bookings
                = bookingRepo
                        .findByShowtime(
                                showtimeId);

        List<BookingDetailResponse> result = new ArrayList<>();

        for (Bookings b : bookings) {

            List<Tickets> tickets
                    = ticketRepo
                            .getTicketsByBooking(
                                    b.getId());

            List<String> seats
                    = tickets.stream()
                            .map(t
                                    -> t.getSeatId()
                                    .getSeatNumber())
                            .toList();

            BookingDetailResponse dto
                    = new BookingDetailResponse();

            dto.setBookingId(
                    b.getId());

            dto.setCustomerName(
                    b.getUserId()
                            .getName());

            dto.setMovieName(
                    b.getShowtimeId()
                            .getMovieId()
                            .getMovieName());

            dto.setSeats(seats);

            dto.setTotalPrice(
                    b.getTotalPrice());

            dto.setCreatedAt(
                    b.getCreatedAt());

            result.add(dto);
        }

        return result;
    }

    @Override
    public List<Bookings> getBookingsByDate(Date date) {
        return bookingRepo.getBookingsByDate(date);
    }
}
