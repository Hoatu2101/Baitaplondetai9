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

@Controller
@RequestMapping("/admin/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String rooms(Model model,
                        @RequestParam Map<String, String> params) {

        model.addAttribute(
                "rooms",
                this.roomService.getRooms(params)
        );

        return "rooms";
    }

    @GetMapping("/create")
    public String createView(Model model) {

        model.addAttribute("room", new Rooms());

        model.addAttribute(
                "cinemas",
                this.cinemaService.getCinemas()
        );

        model.addAttribute(
                "statuses",
                this.statusService.getStatuses()
        );

        return "room-form";
    }

    @PostMapping
    public String addRoom(
            @ModelAttribute(value = "room") Rooms room) {

        this.roomService.addOrUpdate(room);

        return "redirect:/admin/rooms";
    }

    @GetMapping("/{id}")
    public String updateView(Model model,
                             @PathVariable(value = "id") int id) {

        model.addAttribute(
                "room",
                this.roomService.getRoomById(id)
        );

        model.addAttribute(
                "cinemas",
                this.cinemaService.getCinemas()
        );

        model.addAttribute(
                "statuses",
                this.statusService.getStatuses()
        );

        return "room-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(
            @PathVariable(value = "id") int id) {

        this.roomService.deleteRoom(id);

        return "redirect:/admin/rooms";
    }
}