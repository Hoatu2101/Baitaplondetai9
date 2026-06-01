/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */
import com.tth.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/showtimes")
public class ApiSeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/{showtimeId}/seats")
    public ResponseEntity<?> getSeats(
            @PathVariable Integer showtimeId) {

        return ResponseEntity.ok(
                seatService.getSeatsByShowtime(showtimeId));
    }

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
