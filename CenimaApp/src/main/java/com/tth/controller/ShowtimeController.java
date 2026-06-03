/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */
import com.tth.pojo.Showtimes;
import com.tth.repository.ShowtimeRepository;
import com.tth.service.BookingService;
import com.tth.service.MoviesService;
import com.tth.service.RoomService;
import com.tth.service.SeatService;
import com.tth.service.ShowtimeService;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private MoviesService movieService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SeatService seatService;
    
    @Autowired
    private ShowtimeRepository showtimeRepo;

    @GetMapping
    public String showtimes(Model model, @RequestParam Map<String, String> params) {

        model.addAttribute(
                "showtimes",
                showtimeService.getAdminShowtimes());

        return "showtimes";
    }

    @GetMapping("/create")
    public String createView(Model model) {

        model.addAttribute("showtime", new Showtimes());

        model.addAttribute(
                "movies",
                this.movieService.getMovies(null)
        );

        model.addAttribute(
                "rooms",
                this.roomService.getRooms(null)
        );

        return "showtime-form";
    }

    @PostMapping
    public String addShowtime(@ModelAttribute(value = "showtime") Showtimes showtime, Model model) {

        try {

            this.showtimeService.addOrUpdate(showtime);

            return "redirect:/admin/showtimes";

        } catch (RuntimeException ex) {

            model.addAttribute("errMsg", ex.getMessage());

            model.addAttribute(
                    "movies",
                    this.movieService.getMovies(null)
            );

            model.addAttribute(
                    "rooms",
                    this.roomService.getRooms(null)
            );

            return "showtime-form";
        }
    }

    @GetMapping("/{id}")
    public String updateView(Model model, @PathVariable(value = "id") int id) {

        model.addAttribute(
                "showtime",
                this.showtimeService.getShowtimeById(id)
        );

        model.addAttribute(
                "movies",
                this.movieService.getMovies(null)
        );

        model.addAttribute(
                "rooms",
                this.roomService.getRooms(null)
        );

        return "showtime-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteShowtime(@PathVariable("id") int id, RedirectAttributes redirect) {

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

        return "redirect:/admin/showtimes";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {

        model.addAttribute(
                "showtime",
                showtimeService.getShowtimeById(id));

        model.addAttribute(
                "statistic",
                showtimeService.getStatistic(id));

        return "showtime-detail";
    }

    @GetMapping("/{id}/bookings")
    public String bookingList(
            @PathVariable ("id") Integer id,
            Model model) {

        model.addAttribute(
                "details",
                bookingService
                        .getBookingDetailsByShowtime(id));

        return "showtime-bookings";
    }

    @GetMapping("/{id}/seats")
    public String seatMap(@PathVariable ("id") Integer id, Model model) {

        model.addAttribute(
                "showtime",
                showtimeService.getShowtimeById(id));

        model.addAttribute(
                "seats",
                seatService.getSeatsByShowtime(id));

        return "showtime-seat-map";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) String movie,
            @RequestParam(required = false)
            @DateTimeFormat(
                    pattern = "yyyy-MM-dd") Date date,
            Model model) {

        model.addAttribute(
                "showtimes",
                showtimeRepo.searchShowtimes(
                        movie,
                        date));

        return "showtimes";
    }

}
