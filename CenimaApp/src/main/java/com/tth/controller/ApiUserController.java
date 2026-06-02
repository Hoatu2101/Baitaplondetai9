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
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @GetMapping("/staff/showtimes/{id}/statistic")
    public ResponseEntity<?>
            getStatistic(
                    @PathVariable("id") Integer id) {

        return ResponseEntity.ok(
                bookingService
                        .getShowtimeStatistic(
                                id));
    }

    @GetMapping("/staff/bookings")
    public String bookings(
            @RequestParam(name="date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            Model model) {

        if (date == null) {
            date = new Date();
        }

        model.addAttribute(
                "bookings",
                bookingService.getBookingsByDate(date));

        return "staff-bookings-list";
    }
}
