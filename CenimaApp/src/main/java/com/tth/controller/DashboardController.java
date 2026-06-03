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
import com.tth.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dashboard")
    public String dashboard(
            Model model,
            @RequestParam(
                    value = "year",
                    defaultValue = "2026") Integer year) {

        model.addAttribute(
                "movieStats",
                dashboardService.revenueByMovie());

        model.addAttribute(
                "monthStats",
                dashboardService.revenueByMonth(year));

        model.addAttribute(
                "quarterStats",
                dashboardService.revenueByQuarter(year));

        model.addAttribute(
                "yearStats",
                dashboardService.revenueByYear());

        model.addAttribute(
                "ticketCount",
                dashboardService.countSoldTickets());

        model.addAttribute(
                "totalRevenue",
                dashboardService.totalRevenue());

        model.addAttribute(
                "movieCount",
                dashboardService.countMovies());

        model.addAttribute(
                "roomCount",
                dashboardService.countRooms());

        model.addAttribute(
                "showtimeCount",
                dashboardService.countShowtimes());

        model.addAttribute(
                "bookingCount",
                dashboardService.countBookings());
        
        model.addAttribute(
                "topMovies",
                dashboardService.topMovies());

        return "dashboard";
    }

//    @GetMapping("/showtimes/{id}/bookings")
//    public String bookingByShowtime(
//            @PathVariable("id") Integer id,
//            Model model) {
//
//        model.addAttribute(
//                "bookings",
//                bookingService
//                        .getBookingDetailsByShowtime(id));
//
//        return "staff-bookings";
//    }
}
