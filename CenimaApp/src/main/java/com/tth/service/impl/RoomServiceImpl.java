/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;



import com.tth.pojo.Rooms;
import com.tth.repository.RoomRepository;
import com.tth.service.RoomService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Override
    public List<Rooms> getRooms(Map<String, String> params) {
        return this.roomRepo.getRooms(params);
    }

    @Override
    public Rooms getRoomById(int id) {
        return this.roomRepo.getRoomById(id);
    }

    @Override
    public void addOrUpdate(Rooms room) {
        this.roomRepo.addOrUpdate(room);
    }

    @Override
    public void deleteRoom(int id) {
        this.roomRepo.deleteRoom(id);
    }

    @Override
    public long countRooms() {
        return this.roomRepo.countRooms();
    }
}