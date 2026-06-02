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
import com.tth.pojo.Rooms;
import com.tth.pojo.Seats;
import com.tth.pojo.Showtimes;
import com.tth.repository.BookingRepository;
import com.tth.repository.SeatRepository;
import com.tth.service.SeatService;
import com.tth.service.SeatShowtimeStatusService;
import com.tth.service.ShowtimeService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<SeatStatusResponse> getSeatsByShowtime(
            Integer showtimeId) {

        seatStatusService.releaseExpiredLocks();

        Showtimes st
                = showtimeService.getShowtimeById(
                        showtimeId);

        List<Seats> seats
                = seatRepo.getSeatsByRoom(
                        st.getRoomId().getId());

        Map<Integer, String> statusMap
                = seatStatusService
                        .getSeatStatusMap(
                                showtimeId);

        return seats.stream()
                .map(seat -> {

                    String status
                            = statusMap.getOrDefault(
                                    seat.getId(),
                                    "AVAILABLE");

                    return new SeatStatusResponse(
                            seat.getId(),
                            seat.getSeatNumber(),
                            status
                    );
                })
                .toList();
    }

    @Override
    public void generateSeats(Rooms room) {

        int capacity = room.getCapacity();

        int cols = 10;

        for (int i = 0; i < capacity; i++) {

            char row
                    = (char) ('A' + i / cols);

            int col
                    = (i % cols) + 1;

            Seats seat
                    = new Seats();

            seat.setRoomId(room);

            seat.setSeatNumber(
                    row + String.valueOf(col));

            seat.setCreatedAt(
                    new Date());

            seatRepo.addSeat(seat);
        }
    }

    @Override
    public void regenerateSeats(Rooms room) {

        List<Seats> seats
                = seatRepo.getSeatsByRoom(
                        room.getId());

        for (Seats s : seats) {

            if (s.getTicketsList() != null
                    && !s.getTicketsList().isEmpty()) {

                throw new RuntimeException(
                        "Không thể thay đổi sức chứa phòng đã phát sinh vé.");
            }
        }

        seatRepo.deleteSeatsByRoom(
                room.getId());

        generateSeats(room);
    }
}
