/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

/**
 *
 * @author Administrator
 */
import com.tth.pojo.SeatShowtimeStatus;
import com.tth.pojo.SeatStatus;
import com.tth.pojo.Seats;
import com.tth.pojo.Showtimes;
import com.tth.pojo.Users;
import com.tth.repository.DashboardRepository;
import com.tth.repository.SeatRepository;
import com.tth.repository.SeatShowtimeStatusRepository;
import com.tth.repository.ShowtimeRepository;
import com.tth.service.DashboardService;
import com.tth.service.SeatShowtimeStatusService;
import com.tth.service.UserService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeatShowtimeStatusServiceImpl implements SeatShowtimeStatusService {

    @Autowired
    private SeatShowtimeStatusRepository statusRepo;

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private ShowtimeRepository showtimeRepo;

    @Autowired
    private UserService userService;

    private void validateSeatBelongShowtime(Showtimes showtime, Seats seat) {

        if (!seat.getRoomId()
                .getId()
                .equals(
                        showtime
                                .getRoomId()
                                .getId())) {

            throw new RuntimeException(
                    "Ghế không thuộc phòng chiếu");
        }

    }

    @Override
    public boolean isBooked(
            Integer showtimeId,
            Integer seatId) {

        return statusRepo.isBooked(
                showtimeId,
                seatId);
    }

    @Override
    public boolean isLocked(
            Integer showtimeId,
            Integer seatId) {

        return statusRepo.isLocked(
                showtimeId,
                seatId);
    }

    @Override
    public List<SeatShowtimeStatus>
            getByShowtime(
                    Integer showtimeId) {

        return statusRepo
                .getByShowtime(
                        showtimeId);
    }

    @Override
    public Map<Integer, String>
            getSeatStatusMap(
                    Integer showtimeId) {

        List<SeatShowtimeStatus> statuses
                = statusRepo
                        .getByShowtime(
                                showtimeId);

        Map<Integer, String> map
                = new HashMap<>();

        for (SeatShowtimeStatus s
                : statuses) {

            map.put(
                    s.getSeatId().getId(),
                    s.getStatus()
            );
        }

        return map;
    }

    @Override
    public void lockSeats(
            Integer showtimeId,
            List<Integer> seatIds,
            String username) {

        Showtimes showtime
                = showtimeRepo
                        .getShowtimeById(
                                showtimeId);

        Users user
                = userService
                        .getUserByUsername(
                                username);

        for (Integer seatId
                : seatIds) {

            SeatShowtimeStatus status
                    = statusRepo.find(
                            showtimeId,
                            seatId);

            if (status == null) {

                status
                        = new SeatShowtimeStatus();

                status.setShowtimeId(
                        showtime);

                status.setSeatId(
                        seatRepo.getSeatById(
                                seatId));

            }

            if ("BOOKED".equals(
                    status.getStatus())) {

                throw new RuntimeException(
                        "Ghế "
                        + seatId
                        + " đã được đặt");
            }

            if ("LOCKED".equals(
                    status.getStatus())) {

                throw new RuntimeException(
                        "Ghế "
                        + seatId
                        + " đang được người khác giữ");
            }

            status.setStatus(
                    SeatStatus.LOCKED.name());

            status.setUserId(user);

            status.setLockTime(
                    new Date());

            statusRepo.save(
                    status);
        }
    }

    @Override
    public void releaseExpiredLocks() {
        statusRepo.releaseExpiredLocks();
    }
}
