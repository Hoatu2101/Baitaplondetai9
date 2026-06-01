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
import com.tth.service.MoviesService;
import com.tth.service.RoomService;
import com.tth.service.ShowtimeService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private MoviesService movieService;

    @Autowired
    private RoomService roomService;

    @GetMapping
    public String showtimes(
            Model model,
            @RequestParam Map<String, String> params) {

        model.addAttribute(
                "showtimes",
                this.showtimeService.getShowtimes(params)
        );

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
    public String addShowtime(
            @ModelAttribute(value = "showtime") Showtimes showtime,
            Model model) {

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
    public String updateView(
            Model model,
            @PathVariable(value = "id") int id) {

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
    public String deleteShowtime(
            @PathVariable(value = "id") int id) {

        this.showtimeService.deleteShowtime(id);

        return "redirect:/admin/showtimes";
    }
}