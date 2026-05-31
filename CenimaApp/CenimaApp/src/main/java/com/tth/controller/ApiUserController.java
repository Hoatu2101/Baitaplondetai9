/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */
import com.tth.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiUserController {


    @Autowired
    private BookingService bookingService;

    @GetMapping("/{userId}/tickets")
    public ResponseEntity<?> getTickets(
            String userName) {

        return ResponseEntity.ok(
                bookingService.getMyBookings(userName));
    }

}
