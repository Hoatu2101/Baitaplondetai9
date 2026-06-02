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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private SeatService seatService;

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
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "capacity") int capacity,
            @RequestParam(name = "cinemaId") Integer cinemaId,
            @RequestParam(name = "statusId") Integer statusId) {

        Rooms room;

        if (id != null) {
            room = roomService.getRoomById(id);
        } else {
            room = new Rooms();
        }

        room.setName(name);
        room.setCapacity(capacity);

        room.setCinemaId(
                cinemaService.getCinemaById(cinemaId));

        room.setStatusId(
                statusService.getStatusById(statusId));

        roomService.addOrUpdate(room);

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
            @PathVariable("id") int id,
            RedirectAttributes redirect) {

        try {

            roomService.deleteRoom(id);

            redirect.addFlashAttribute(
                    "success",
                    "Đóng phòng thành công !");

        } catch (Exception ex) {

            redirect.addFlashAttribute(
                    "error",
                    ex.getMessage());
        }

        return "redirect:/admin/rooms";
    }

    @GetMapping("/reopen/{id}")
    public String reopenRoom(
            @PathVariable("id") int id,
            RedirectAttributes redirect) {

        roomService.reopenRoom(id);

        redirect.addFlashAttribute(
                "success",
                "Mở lại phòng thành công");

        return "redirect:/admin/rooms";
    }

    @GetMapping("/{id}/seats")
    public String roomSeats(
            @PathVariable("id") int id,
            Model model) {

        model.addAttribute(
                "room",
                roomService.getRoomById(id));

        model.addAttribute(
                "seats",
                seatService.getSeatsByRoom(id));

        return "room-seats";
    }
}
