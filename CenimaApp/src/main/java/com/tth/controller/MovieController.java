/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.tth.pojo.Movies;
import com.tth.service.MoviesService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/admin")
public class MovieController {
    @Autowired
    private MoviesService MovieService;

    @GetMapping("/movies")
    public String createView(Model model) {
        model.addAttribute("movies", new Movies());
        return "movies";
    }

    @PostMapping("/movies")
    public String create(@ModelAttribute(value = "movies") Movies m) {
        this.MovieService.addOrUpdateMovies(m);
        return "redirect:/";
    }

    @GetMapping("/movies/{moviesId}")
    public String updateView(Model model, @PathVariable(value = "moviesId") int id) {
        model.addAttribute("movies", this.MovieService.getMoviestById(id));

        return "movies";
    }

}
