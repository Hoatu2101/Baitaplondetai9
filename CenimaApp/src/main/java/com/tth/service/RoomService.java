/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */


import com.tth.pojo.Rooms;
import java.util.List;
import java.util.Map;

public interface RoomService {

    List<Rooms> getRooms(Map<String, String> params);

    Rooms getRoomById(int id);

    void addOrUpdate(Rooms room);

    void deleteRoom(int id);
    
    void reopenRoom(int id);

    long countRooms();
    
}