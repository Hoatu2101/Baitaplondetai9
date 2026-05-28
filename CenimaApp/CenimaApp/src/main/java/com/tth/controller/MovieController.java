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
    private CategoryServices cateService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/movies")
    public String movies(Model model,
            @RequestParam(value = "kw", required = false) String kw,
            @RequestParam(value = "cateId", required = false) Integer cateId,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        model.addAttribute("movies",
                this.movieService.getMovies(kw, cateId, page));

        model.addAttribute("categories",
                cateService.getCates());

        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String details(Model model,
            @PathVariable(value = "id") int id) {

        Movies movie = this.movieService.getMovieById(id);

        if (movie == null) {
            return "redirect:/movies";
        }

        model.addAttribute("movie", movie);

        return "movie-details";
    }

    @GetMapping("/admin/movies")
    public String createView(Model model) {

        model.addAttribute("movie", new Movies());

        model.addAttribute("categories",
                cateService.getCates());

        return "movie-form";
    }

    @GetMapping("/admin/movies/{id}")
    public String updateView(Model model,
            @PathVariable(value = "id") int id) {

        model.addAttribute("movie",
                movieService.getMovieById(id));

        model.addAttribute("categories",
                cateService.getCates());

        return "movie-form";
    }

    @PostMapping("/admin/movies")
    public String addMovie(
            @ModelAttribute("movie") Movies movie,
            @RequestParam("categoryId") int categoryId,
            @RequestParam(value = "file", required = false) MultipartFile file)
            throws IOException {

        Categories c = new Categories();
        c.setId(categoryId);

        movie.setCategory(c);

        if (file != null && !file.isEmpty()) {

            Map res = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap());

            movie.setPoster(
                    res.get("secure_url").toString());
        }

        movieService.addOrUpdate(movie);

        return "redirect:/movies";
    }

    @GetMapping("/admin/deleteMovie/{id}")
    public String deleteMovie(
            @PathVariable(value = "id") int id) {

        movieService.deleteMovie(id);

        return "redirect:/movies";
    }
}
