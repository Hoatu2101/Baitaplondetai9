/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;


import com.tth.service.BookingService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/admin/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String bookingByDate(
            @RequestParam(required = false)
            @DateTimeFormat(pattern="yyyy-MM-dd")
            Date date,
            Model model){

        if(date != null){

            model.addAttribute(
                    "bookings",
                    bookingService.getBookingsByDate(date));
        }

        return "booking-report";
    }
}