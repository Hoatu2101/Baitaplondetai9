/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;


/**
 *
 * @author Admin
 */

import com.tth.pojo.Seats;
import com.tth.repository.SeatRepository;
import com.tth.service.SeatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepo;

    @Override
    public Seats getSeatById(int id) {
        return seatRepo.getSeatById(id);
    }

    @Override
    public List<Seats> getSeatsByRoom(int roomId) {
        return seatRepo.getSeatsByRoom(roomId);
    }

    @Override
    public List<Seats> getAvailableSeats(int roomId) {
        return seatRepo.getAvailableSeats(roomId);
    }

    @Override
    public void lockSeat(int seatId) {

        Seats seat = seatRepo.getSeatById(seatId);

        seat.setIsAvailable(false);

        seatRepo.updateSeat(seat);
    }

    @Override
    public void unlockSeat(int seatId) {

        Seats seat = seatRepo.getSeatById(seatId);

        seat.setIsAvailable(true);

        seatRepo.updateSeat(seat);
    }
}