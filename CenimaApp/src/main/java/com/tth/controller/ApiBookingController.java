/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */
import com.tth.dto.BookingRequest;
import com.tth.pojo.Bookings;
import com.tth.service.BookingService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secure")
public class ApiBookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(
            @RequestBody BookingRequest request,
            Principal principal) {

        try {

            Bookings booking
                    = bookingService
                            .bookTicket(
                                    request
                                            .getShowtimeId(),
                                    request
                                            .getSeatId(),
                                    principal
                                            .getName()
                            );

            return ResponseEntity.ok(
                    booking.getId());

        } catch (Exception ex) {

            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @GetMapping("/seats/check")
    public ResponseEntity<?> checkSeat(
            @RequestParam Integer showtimeId,
            @RequestParam Integer seatId) {

        boolean booked
                = bookingService
                        .isSeatBooked(
                                showtimeId,
                                seatId);

        return ResponseEntity.ok(
                Map.of(
                        "booked",
                        booked
                )
        );
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<?> myBookings(
            Principal principal) {

        return ResponseEntity.ok(
                bookingService
                        .getMyBookings(
                                principal.getName()
                        )
        );
    }

    @GetMapping("/staff/bookings")
    public String bookings(Model model, String username) {

        model.addAttribute(
                "bookings",
                bookingService.getMyBookings(username));

        return "staff-bookings";
    }
}
