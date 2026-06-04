/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */
//import com.tth.pojo.Bookings;
//import com.tth.pojo.Tickets;
//import com.tth.repository.TicketRepository;
//import com.tth.service.BookingService;
//import com.tth.service.DashboardService;
//import com.tth.service.ShowtimeService;
//import java.text.SimpleDateFormat;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.expression.ParseException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/staff")
//public class StaffDashboardController {
//
//    @Autowired
//    private DashboardService dashboardService;
//
//    @Autowired
//    private ShowtimeService showtimeService;
//
//    @Autowired
//    private BookingService bookingService;
//    
//    @Autowired
//    private TicketRepository ticketRepo;
//
//    @GetMapping("/dashboard")
//    public String dashboard(
//            Model model) {
//
//        model.addAttribute(
//                "dashboard",
//                dashboardService
//                        .getStaffDashboard());
//
//        model.addAttribute(
//                "showtimes",
//                showtimeService
//                        .getTodayShowtimes());
//
//        return "staff-dashboard";
//    }
//
//    @GetMapping("/bookings")
//    public String bookings(
//            @RequestParam(required = false) String date,
//            Model model) {
//
//        List<Bookings> bookings;
//
//        if (date != null && !date.isEmpty()) {
//
//            try {
//
//                Date d = new SimpleDateFormat(
//                        "yyyy-MM-dd")
//                        .parse(date);
//
//                bookings = bookingService.getBookingsByDate(d);
//
//            } catch (ParseException ex) {
//
//                bookings = Collections.emptyList();
//
//                model.addAttribute(
//                        "error",
//                        "Ngày không hợp lệ");
//            }
//
//        } else {
//
//            bookings = Collections.emptyList();
//        }
//
//        model.addAttribute(
//                "bookings",
//                bookings);
//
//        model.addAttribute(
//                "selectedDate",
//                date);
//
//        return "staff-bookings";
//    }
//
//    @GetMapping("/check-ticket")
//    public String checkTicket(
//            @RequestParam(required = false) Integer ticketId,
//            Model model) {
//
//        if (ticketId != null) {
//
//            Tickets ticket
//                    = ticketRepo.getTicketById(
//                            ticketId);
//
//            if (ticket == null) {
//
//                model.addAttribute(
//                        "error",
//                        "Không tìm thấy vé");
//
//            } else {
//
//                model.addAttribute(
//                        "ticket",
//                        ticket);
//            }
//        }
//
//        return "check-ticket";
//    }
//    
//}
import com.tth.dto.ShowtimeForm;
import com.tth.pojo.Bookings;
import com.tth.pojo.Movies;
import com.tth.pojo.Rooms;
import com.tth.pojo.Showtimes;
import com.tth.pojo.Tickets;
import com.tth.repository.TicketRepository;
import com.tth.service.BookingService;
import com.tth.service.DashboardService;
import com.tth.service.MoviesService;
import com.tth.service.RoomService;
import com.tth.service.ShowtimeService;
import com.tth.service.TicketService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/staff")
public class StaffDashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MoviesService movieService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/dashboard")
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

    @GetMapping("/bookings")
    public String staffBookings(
            @RequestParam(
                    value = "date",
                    required = false)
            @DateTimeFormat(
                    pattern = "yyyy-MM-dd") Date date,
            Model model) {

        List<Bookings> bookings;

        if (date != null) {
            bookings = bookingService.getBookingsByDate(date);
        } else {
            bookings = bookingService.getBookingsByDate(new Date());
        }

        model.addAttribute(
                "bookings",
                bookings);

        return "staff-bookings";
    }

    @GetMapping("/check-ticket")
    public String checkTicket(
            @RequestParam(
                    value = "ticketId",
                    required = false) Integer ticketId,
            Model model) {

        if (ticketId != null) {

            Tickets ticket
                    = ticketService
                            .getTicketById(ticketId);

            model.addAttribute(
                    "ticket",
                    ticket);
        }

        return "check-ticket";
    }

//    @GetMapping("/showtimes/edit/{id}")
//    public String editShowtime(
//            @PathVariable("id") int id,
//            Model model) {
//
//        model.addAttribute(
//                "showtime",
//                showtimeService.getShowtimeById(id));
//
//        model.addAttribute(
//                "movies",
//                movieService.getMovies(null));
//
//        model.addAttribute(
//                "rooms",
//                roomService.getRooms(null));
//
//        return "staff-showtime-form";
//    }
    @GetMapping("/showtimes/edit/{id}")
    public String editShowtime(
            @PathVariable("id") int id,
            Model model) {

        Showtimes st
                = showtimeService.getShowtimeById(id);

        ShowtimeForm form
                = new ShowtimeForm();

        form.setId(st.getId());

        form.setMovieId(
                st.getMovieId().getId());

        form.setRoomId(
                st.getRoomId().getId());

        form.setStartTime(
                st.getStartTime());

        form.setEndTime(
                st.getEndTime());

        model.addAttribute(
                "showtime",
                form);

        model.addAttribute(
                "movies",
                movieService.getMovies(
                        new HashMap<>()));

        model.addAttribute(
                "rooms",
                roomService.getRooms(
                        new HashMap<>()));

        return "staff-showtime-form";
    }

    @PostMapping("/showtimes/update")
    public String updateShowtime(
            @ModelAttribute("showtime") ShowtimeForm form,
            Model model,
            RedirectAttributes redirect) {

        try {

            Showtimes st
                    = showtimeService
                            .getShowtimeById(
                                    form.getId());

            Movies movie
                    = movieService.getMovieById(
                            form.getMovieId());

            Rooms room
                    = roomService.getRoomById(
                            form.getRoomId());

            st.setMovieId(movie);

            st.setRoomId(room);

            st.setStartTime(
                    form.getStartTime());

            st.setEndTime(
                    form.getEndTime());

            showtimeService
                    .addOrUpdate(st);

            redirect.addFlashAttribute(
                    "success",
                    "Cập nhật thành công");

            return "redirect:/staff/dashboard";

        } catch (Exception ex) {

            model.addAttribute(
                    "errMsg",
                    ex.getMessage());

            model.addAttribute(
                    "movies",
                    movieService.getMovies(
                            new HashMap<>()));

            model.addAttribute(
                    "rooms",
                    roomService.getRooms(
                            new HashMap<>()));

            return "staff-showtime-form";
        }
    }

    @GetMapping("/showtimes/create")
    public String createShowtime(Model model) {

        Showtimes showtime = new Showtimes();

        model.addAttribute(
                "showtime",
                showtime);

        model.addAttribute(
                "movies",
                movieService.getMovies(
                        new HashMap<>()));

        model.addAttribute(
                "rooms",
                roomService.getRooms(
                        new HashMap<>()));

        return "staff-showtime-form";
    }

    @PostMapping("/showtimes/create")
    public String saveShowtime(
            @ModelAttribute("showtime") ShowtimeForm form,
            Model model,
            RedirectAttributes redirect) {

        try {

            Movies movie
                    = movieService.getMovieById(
                            form.getMovieId());

            Rooms room
                    = roomService.getRoomById(
                            form.getRoomId());

            Showtimes st
                    = new Showtimes();

            st.setMovieId(movie);

            st.setRoomId(room);

            st.setStartTime(
                    form.getStartTime());

            st.setEndTime(
                    form.getEndTime());

            showtimeService.addOrUpdate(st);

            redirect.addFlashAttribute(
                    "success",
                    "Thêm suất chiếu thành công");

            return "redirect:/staff/dashboard";

        } catch (Exception ex) {

            model.addAttribute(
                    "errMsg",
                    ex.getMessage());

            model.addAttribute(
                    "movies",
                    movieService.getMovies(
                            new HashMap<>()));

            model.addAttribute(
                    "rooms",
                    roomService.getRooms(
                            new HashMap<>()));

            return "staff-showtime-form";
        }
    }

    @GetMapping("/showtimes/delete/{id}")
    public String deleteShowtime(
            @PathVariable("id") int id,
            RedirectAttributes redirect) {

        try {

            showtimeService.deleteShowtime(id);

            redirect.addFlashAttribute(
                    "success",
                    "Xóa thành công");

        } catch (Exception ex) {

            redirect.addFlashAttribute(
                    "error",
                    ex.getMessage());
        }

        return "redirect:/staff/dashboard";
    }

}
