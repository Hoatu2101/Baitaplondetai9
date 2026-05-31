/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

/**
 *
 * @author Admin
 */
import com.tth.dto.SeatStatusResponse;
import com.tth.pojo.Seats;
import com.tth.pojo.Showtimes;
import com.tth.repository.BookingRepository;
import com.tth.repository.SeatRepository;
import com.tth.service.SeatService;
import com.tth.service.SeatShowtimeStatusService;
import com.tth.service.ShowtimeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
//@Transactional
//public class SeatServiceImpl implements SeatService {
//
//    @Autowired
//    private ShowtimeService showtimeService;
//
//    @Autowired
//    private BookingRepository bookingRepo;
//
//    @Autowired
//    private SeatRepository seatRepo;
//
//    @Override
//    public Seats getSeatById(int id) {
//        return seatRepo.getSeatById(id);
//    }
//
//    @Override
//    public List<Seats> getSeatsByRoom(int roomId) {
//        return seatRepo.getSeatsByRoom(roomId);
//    }
//
//    @Override
//    public List<SeatStatusResponse> getSeatsByShowtime(
//            Integer showtimeId) {
//
//        Showtimes st
//                = showtimeService
//                        .getShowtimeById(
//                                showtimeId);
//
//        List<Seats> seats
//                = seatRepo.getSeatsByRoom(
//                        st.getRoomId().getId());
//
//        List<Integer> bookedSeats
//                = bookingRepo.getBookedSeatIds(
//                        showtimeId);
//
//        return seats.stream()
//                .map(s -> new SeatStatusResponse(
//                s.getId(),
//                s.getSeatNumber(),
//                bookedSeats.contains(
//                        s.getId())
//        ))
//                .toList();
//    }
//
//
//}

@Service
@Transactional
public class SeatServiceImpl
        implements SeatService {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private SeatShowtimeStatusService seatStatusService;

    @Override
    public Seats getSeatById(int id) {
        return seatRepo.getSeatById(id);
    }

    @Override
    public List<Seats> getSeatsByRoom(
            int roomId) {

        return seatRepo.getSeatsByRoom(
                roomId);
    }

    @Override
    public List<SeatStatusResponse>
            getSeatsByShowtime(
                    Integer showtimeId) {

        Showtimes showtime
                = showtimeService
                        .getShowtimeById(
                                showtimeId);

        List<Seats> seats
                = seatRepo.getSeatsByRoom(
                        showtime
                                .getRoomId()
                                .getId());

        Map<Integer, String> statuses
                = seatStatusService
                        .getSeatStatusMap(
                                showtimeId);

        return seats.stream()
                .map(seat ->
                        new SeatStatusResponse(
                                seat.getId(),
                                seat.getSeatNumber(),
                                statuses.getOrDefault(
                                        seat.getId(),
                                        "AVAILABLE"
                                )
                        )
                )
                .toList();
    }
}