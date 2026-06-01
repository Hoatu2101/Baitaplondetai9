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

//@Controller
//public class DashboardController {
//
//    @Autowired
//    private DashboardService dashboardService;
//
//    @GetMapping("/admin/dashboard")
//    public String dashboard(Model model)
//            throws JsonProcessingException {
//
//        model.addAttribute("movieCount",
//                this.dashboardService.countMovies());
//
//        model.addAttribute("roomCount",
//                this.dashboardService.countRooms());
//
//        model.addAttribute("showtimeCount",
//                this.dashboardService.countShowtimes());
//
//        model.addAttribute("bookingCount",
//                this.dashboardService.countBookings());
//
//        model.addAttribute("revenue",
//                this.dashboardService.totalRevenue());
//
//        List<Object[]> revenueData =
//                this.dashboardService.revenueByMovie();
//
//        List<String> movieNames = new ArrayList<>();
//
//        List<Double> movieRevenue = new ArrayList<>();
//
//        for (Object[] obj : revenueData) {
//
//            movieNames.add(obj[0].toString());
//
//            movieRevenue.add(
//                    Double.valueOf(obj[1].toString())
//            );
//        }
//
//        List<Object[]> bookingData =
//                this.dashboardService.bookingByDate();
//
//        List<String> dates = new ArrayList<>();
//
//        List<Long> bookingCounts = new ArrayList<>();
//
//        for (Object[] obj : bookingData) {
//
//            dates.add(obj[0].toString());
//
//            bookingCounts.add(
//                    Long.valueOf(obj[1].toString())
//            );
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        model.addAttribute("movieLabels",
//                mapper.writeValueAsString(movieNames));
//
//        model.addAttribute("movieRevenue",
//                mapper.writeValueAsString(movieRevenue));
//
//        model.addAttribute("bookingDates",
//                mapper.writeValueAsString(dates));
//
//        model.addAttribute("bookingCounts",
//                mapper.writeValueAsString(bookingCounts));
//
//        return "dashboard";
//    }
//}
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

    @GetMapping("/showtimes/{id}/bookings")
    public String bookingByShowtime(
            @PathVariable("id") Integer id,
            Model model) {

        model.addAttribute(
                "bookings",
                bookingService
                        .getBookingDetailsByShowtime(id));

        return "staff-bookings";
    }
}
