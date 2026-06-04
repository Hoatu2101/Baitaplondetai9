/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */
import com.tth.pojo.Users;
import com.tth.service.BookingService;
import com.tth.service.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiUserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/users")
    public ResponseEntity<Users> create(@RequestParam Map<String, String> info, 
            @RequestParam(value = "avatar") MultipartFile avatar) {
        Users u = this.userService.addUser(info, avatar);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
    }
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
@RequestMapping("/secure/profile")
@ResponseBody
@CrossOrigin
public ResponseEntity<Users> getProfile(Principal principal) {
    return new ResponseEntity<>(
        this.userService.getUserByUsername(principal.getName()),
        HttpStatus.OK
    );
}
}
