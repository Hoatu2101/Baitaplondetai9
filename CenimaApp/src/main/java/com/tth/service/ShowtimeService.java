/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */
import com.tth.dto.ShowtimeAdminDTO;
import com.tth.dto.ShowtimeStatisticResponse;
import com.tth.pojo.Showtimes;
import java.util.List;
import java.util.Map;

public interface ShowtimeService {

    List<Showtimes> getShowtimes(Map<String, String> params);

    Showtimes getShowtimeById(int id);

    void addOrUpdate(Showtimes showtime);

    void deleteShowtime(int id);

    boolean isRoomBusy(int roomId, java.util.Date start, java.util.Date end, Integer showtimeId);

    long countSoldTickets(Integer showtimeId);

    double revenueByShowtime(Integer showtimeId);

    ShowtimeStatisticResponse getStatistic(Integer showtimeId);

    List<Showtimes> getUpcomingShowtimes();

    List<Showtimes> getTodayShowtimes();
    
    List<ShowtimeAdminDTO> getAdminShowtimes();
}
