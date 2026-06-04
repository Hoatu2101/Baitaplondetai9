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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
                    defaultValue = "2026") Integer year,
            @RequestParam(
                    value = "fromDate",
                    required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(
                    value = "toDate",
                    required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

        model.addAttribute(
                "movieStats",
                dashboardService.revenueByMovie());

        model.addAttribute(
                "dateStats",
                dashboardService.revenueByDate(
                        fromDate,
                        toDate));

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

        model.addAttribute(
                "topCustomers",
                dashboardService.topCustomers());

        List<String> movieLabels = new ArrayList<>();
        List<Double> movieValues = new ArrayList<>();

        for (Object[] o : dashboardService.revenueByMovie()) {

            movieLabels.add(String.valueOf(o[0]));

            movieValues.add(
                    ((Number) o[1]).doubleValue());
        }

        model.addAttribute(
                "movieLabels",
                movieLabels);

        model.addAttribute(
                "movieValues",
                movieValues);

        return "dashboard";
    }

}
