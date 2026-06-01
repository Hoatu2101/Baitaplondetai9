/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */
import com.tth.pojo.SeatShowtimeStatus;
import java.util.List;
import java.util.Map;

public interface SeatShowtimeStatusService {

    boolean isBooked(
            Integer showtimeId,
            Integer seatId);

    boolean isLocked(
            Integer showtimeId,
            Integer seatId);

    void lockSeats(
            Integer showtimeId,
            List<Integer> seatIds,
            String username);

    List<SeatShowtimeStatus>
            getByShowtime(
                    Integer showtimeId);

    Map<Integer, String>
            getSeatStatusMap(
                    Integer showtimeId);

    void releaseExpiredLocks();
}
