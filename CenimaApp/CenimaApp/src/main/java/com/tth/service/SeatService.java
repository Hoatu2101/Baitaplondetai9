/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */

import com.tth.pojo.Seats;
import java.util.List;

public interface SeatService {

    Seats getSeatById(int id);

    List<Seats> getSeatsByRoom(int roomId);

    List<Seats> getAvailableSeats(int roomId);

    void lockSeat(int seatId);

    void unlockSeat(int seatId);
}