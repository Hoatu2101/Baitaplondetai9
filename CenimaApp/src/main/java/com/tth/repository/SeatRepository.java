package com.tth.repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
import com.tth.pojo.Seats;
import java.util.List;
import java.util.Map;

public interface SeatRepository {

    Seats getSeatById(int id);

    List<Seats> getSeatsByRoom(int roomId);

    void addSeat(Seats seat);

    void deleteSeatsByRoom(int roomId);


}
