/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository;

/**
 *
 * @author Admin
 */
import com.tth.pojo.Showtimes;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ShowtimeRepository {

    List<Showtimes> getShowtimes(Map<String, String> params);

    Showtimes getShowtimeById(int id);

    void addOrUpdate(Showtimes showtime);

    void deleteShowtime(int id);

    long countAvailableSeats(int showtimeId);

    boolean isRoomBusy(int roomId, java.util.Date start, java.util.Date end, Integer showtimeId);

    long countSoldTickets(Integer showtimeId);

    double revenueByShowtime(Integer showtimeId);

    List<Showtimes> getShowtimesByMovie(Integer movieId);

    List<Showtimes> getShowtimesByRoom(Integer roomId);

    List<Showtimes> getUpcomingShowtimes();

    List<Showtimes> getTodayShowtimes();
    
    List<Showtimes> searchShowtimes(String movie, Date date);
}
