/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;


/**
 *
 * @author Admin
 */

import com.tth.pojo.Showtimes;
import com.tth.repository.ShowtimeRepository;
import com.tth.service.ShowtimeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowtimeServiceImpl implements ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepo;

    @Override
    public List<Showtimes> getShowtimes(Map<String, String> params) {
        return this.showtimeRepo.getShowtimes(params);
    }

    @Override
    public Showtimes getShowtimeById(int id) {
        return this.showtimeRepo.getShowtimeById(id);
    }

    @Override
    public void addOrUpdate(Showtimes showtime) {

        boolean busy = this.showtimeRepo.isRoomBusy(
                showtime.getRoomId().getId(),
                showtime.getStartTime(),
                showtime.getEndTime(),
                showtime.getId()
        );

        if (busy)
            throw new RuntimeException(
                    "Phòng đã có lịch chiếu trong thời gian này!"
            );

        this.showtimeRepo.addOrUpdate(showtime);
    }

    @Override
    public void deleteShowtime(int id) {
        this.showtimeRepo.deleteShowtime(id);
    }

    @Override
    public boolean isRoomBusy(
            int roomId,
            java.util.Date start,
            java.util.Date end,
            Integer showtimeId) {

        return this.showtimeRepo.isRoomBusy(
                roomId,
                start,
                end,
                showtimeId
        );
    }
    @Override
public long countAvailableSeats(int showtimeId) {
    return showtimeRepo.countAvailableSeats(showtimeId);
}
}