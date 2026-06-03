/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */

import com.tth.service.DashboardService;
import com.tth.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff/dashboard")
public class StaffDashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping
    public String dashboard(
            Model model) {

        model.addAttribute(
                "dashboard",
                dashboardService
                        .getStaffDashboard());

        model.addAttribute(
                "showtimes",
                showtimeService
                        .getTodayShowtimes());

        return "staff-dashboard";
    }
}