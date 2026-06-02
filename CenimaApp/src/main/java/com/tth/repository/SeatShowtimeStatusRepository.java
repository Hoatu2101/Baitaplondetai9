/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository;

import com.tth.pojo.SeatShowtimeStatus;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface SeatShowtimeStatusRepository {

    SeatShowtimeStatus find(
            Integer showtimeId,
            Integer seatId);

    SeatShowtimeStatus findForUpdate( // Chong dat trung ve
                    Integer showtimeId,
                    Integer seatId);

    void save(
            SeatShowtimeStatus status);

    List<SeatShowtimeStatus> getByShowtime(
            Integer showtimeId);

    boolean isBooked(
            Integer showtimeId,
            Integer seatId);

    boolean isLocked(
            Integer showtimeId,
            Integer seatId);

    void releaseExpiredLocks();

}
