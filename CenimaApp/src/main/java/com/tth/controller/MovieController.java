/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.tth.pojo.Movies;
import com.tth.service.CategoryServices;
import com.tth.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/admin")
public class MovieController {

    @Autowired
    private MoviesService movieService;

    @Autowired
    private CategoryServices categoryService;

    @GetMapping("/movies")
    public String createView(Model model) {

        model.addAttribute("movie", new Movies());

        model.addAttribute(
                "categories",
                this.categoryService.getCates()
        );

        return "movies";
    }

    @PostMapping("/movies")
    public String create(
            @ModelAttribute("movie") Movies m) {

        this.movieService.addOrUpdateMovies(m);

        return "redirect:/";
    }

    @GetMapping("/movies/{id}")
    public String updateView(
            Model model,
            @PathVariable("id") int id) {

        model.addAttribute(
                "movie",
                this.movieService.getMoviestById(id)
        );

        model.addAttribute(
                "categories",
                this.categoryService.getCates()
        );

        return "movies";
    }
}