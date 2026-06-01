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
import com.tth.service.SeatService;
import com.tth.service.SeatShowtimeStatusService;
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
    private SeatShowtimeStatusService seatStatusService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private BookingService bookingService;

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

//    @GetMapping(
//            "/showtimes/{id}/seats")
//    public ResponseEntity<?>
//            getSeatsByShowtime(
//                    @PathVariable Integer id) {
//
//        return ResponseEntity.ok(
//                seatService
//                        .getSeatsByShowtime(
//                                id));
//    }
    @PostMapping("/lock-seats")
    public ResponseEntity<?> lockSeats(@RequestBody BookingRequest request, Principal principal) {

        try {

            seatStatusService.lockSeats(
                    request.getShowtimeId(),
                    request.getSeatIds(),
                    principal.getName());

            return ResponseEntity.ok(
                    Map.of(
                            "message",
                            "Lock seat success"));
        } catch (Exception ex) {

            return ResponseEntity.badRequest()
                    .body(
                            Map.of(
                                    "error",
                                    ex.getMessage()));
        }
    }

    @PostMapping("/bookings")
    public ResponseEntity<?>
            createBooking(
                    @RequestBody BookingRequest request,
                    Principal principal) {

        Bookings booking
                = bookingService
                        .bookTickets(
                                request.getShowtimeId(),
                                request.getSeatIds(),
                                principal.getName());

        return ResponseEntity.ok(
                booking.getId());
    }

    @GetMapping("/staff/showtimes/{id}/seats")
    public ResponseEntity<?>
            getSeatMap(
                    @PathVariable ("id") Integer id) {

        return ResponseEntity.ok(
                seatService
                        .getSeatsByShowtime(id));
    }
}
