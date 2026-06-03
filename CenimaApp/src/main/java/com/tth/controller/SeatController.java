/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;


import com.tth.pojo.Rooms;
import com.tth.service.RoomService;
import com.tth.service.StatusService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.tth.service.CinemaService;
import com.tth.service.SeatService;

@Controller
public class SeatController {

    @Autowired
    private SeatService seatService;
    
    @GetMapping("/staff/showtimes/{id}/seat-map")
    public String seatMap(
            @PathVariable ("id") Integer id,
            Model model) {

        model.addAttribute(
                "seats",
                seatService
                        .getSeatsByShowtime(id));

        return "staff-seat-map";
    }
    
    
}