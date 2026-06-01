/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tth.pojo.Categories;
import com.tth.pojo.Movies;
import com.tth.service.CategoryServices;
import com.tth.service.MoviesService;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
@Controller
public class MovieController {

    @Autowired
    private MoviesService movieService;

    @Autowired
    private CategoryServices categoryService;

    @GetMapping("/movies")
    public String movies(
            Model model,
            @RequestParam Map<String, String> params) {

        model.addAttribute(
                "movies",
                movieService.getMovies(
                        params));

        model.addAttribute("currentPage",
                Integer.valueOf(
                        params.getOrDefault(
                                "page",
                                "1")));

        model.addAttribute(
                "totalMovies",
                movieService.countMovies());

        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String details(
            Model model,
            @PathVariable(value = "id") int id) {

        model.addAttribute(
                "movie",
                this.movieService.getMovieById(id)
        );

        return "movie-details";
    }

    @GetMapping("/admin/movies")
    public String createView(Model model) {

        model.addAttribute("movie", new Movies());

        model.addAttribute(
                "categories",
                this.categoryService.getCates()
        );

        return "movie-form";
    }

    @PostMapping("/admin/movies")
    public String addMovie(
            @ModelAttribute(value = "movie") Movies movie) {

        this.movieService.addOrUpdate(movie);

        return "redirect:/movies";
    }

    @GetMapping("/admin/movies/{id}")
    public String updateView(
            Model model,
            @PathVariable(value = "id") int id) {

        model.addAttribute(
                "movie",
                this.movieService.getMovieById(id)
        );

        model.addAttribute(
                "categories",
                this.categoryService.getCates()
        );

        return "movie-form";
    }

    @GetMapping("/admin/movies/delete/{id}")
    public String deleteMovie(
            @PathVariable(value = "id") int id) {

        this.movieService.deleteMovie(id);

        return "redirect:/movies";
    }
}
