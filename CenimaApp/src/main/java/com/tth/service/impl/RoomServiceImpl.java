/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

import com.tth.pojo.Rooms;
import com.tth.pojo.Seats;
import com.tth.pojo.Status;
import com.tth.repository.RoomRepository;
import com.tth.service.RoomService;
import com.tth.service.SeatService;
import com.tth.service.StatusService;
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

    @Autowired
    private SeatService seatService;
    
    @Autowired
    private StatusService statusService;

    @Override
    public List<Rooms> getRooms(Map<String, String> params) {
        return this.roomRepo.getRooms(params);
    }

    @Override
    public Rooms getRoomById(int id) {
        return this.roomRepo.getRoomById(id);
    }

    @Override
    public void deleteRoom(int id) {

        Rooms room
                = roomRepo.getRoomById(id);

        if (room == null) {
            throw new RuntimeException(
                    "Không tìm thấy phòng");
        }

        if (room.getStatusId() != null
                && room.getStatusId().getId() == 3) {
            throw new RuntimeException(
                    "Phòng đã ngưng hoạt động");
        }

        roomRepo.deleteRoom(id);
    }

    @Override
    public void reopenRoom(int id) {

        Rooms room = roomRepo.getRoomById(id);

        Status active
                = statusService.getStatusById(1);

        room.setStatusId(active);

        roomRepo.addOrUpdate(room);
    }

    @Override
    public long countRooms() {
        return this.roomRepo.countRooms();
    }

    @Override
    public void addOrUpdate(Rooms room) {

        boolean isNew
                = room.getId() == null;

        roomRepo.addOrUpdate(room);

        if (isNew) {

            seatService.generateSeats(
                    room);

        } else {

            seatService.regenerateSeats(
                    room);
        }
    }

}
