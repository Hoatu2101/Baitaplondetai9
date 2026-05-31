package com.tth.repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */

import com.tth.pojo.Rooms;
import java.util.List;
import java.util.Map;

public interface RoomRepository {

    List<Rooms> getRooms(Map<String, String> params);

    Rooms getRoomById(int id);

    void addOrUpdate(Rooms room);

    void deleteRoom(int id);

    long countRooms();
}
