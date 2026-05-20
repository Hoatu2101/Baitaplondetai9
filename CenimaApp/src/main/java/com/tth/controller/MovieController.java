/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.tth.pojo.Movies;
import com.tth.service.CategoryServices;
import com.tth.service.MoviesService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
//@Controller
//@RequestMapping("/movies")
//public class MovieController {
//
//    @Autowired
//    private MoviesService movieService;
//
//    @Autowired
//    private CategoryServices cateService;
//
////    @GetMapping("/movies")
////    public String createView(Model model) {
////
////        model.addAttribute("movie", new Movies());
////
////        model.addAttribute(
////                "categories",
////                this.categoryService.getCates()
////        );
////
////        return "movies";
////    }
////
////    @PostMapping("/movies")
////    public String create(
////            @ModelAttribute("movie") Movies m) {
////
////        this.movieService.addOrUpdateMovies(m);
////
////        return "redirect:/";
////    }
////
////    @GetMapping("/movies/{id}")
////    public String updateView(
////            Model model,
////            @PathVariable("id") int id) {
////
////        model.addAttribute(
////                "movie",
////                this.movieService.getMoviestById(id)
////        );
////
////        model.addAttribute(
////                "categories",
////                this.categoryService.getCates()
////        );
////
////        return "movies";
////    }
//    
//    
//    @GetMapping
//    public String movies(Model model,
//            @RequestParam Map<String, String> params) {
//
//        model.addAttribute(
//                "movies",
//                this.movieService.getMovies(params)
//        );
//
//        return "movies";
//    }
//
//    @GetMapping("/create")
//    public String createView(Model model) {
//
//        model.addAttribute("movie", new Movies());
//
//        model.addAttribute(
//                "categories",
//                this.cateService.getCates()
//        );
//
//        return "movie-form";
//    }
//
//    @PostMapping("/create")
//    public String createMovie(
//            @ModelAttribute(value = "movie") Movies movie) {
//
//        this.movieService.addOrUpdateMovies(movie);
//
//        return "redirect:/movies";
//    }
//
//    @GetMapping("/{id}")
//    public String details(Model model,
//            @PathVariable(value = "id") int id) {
//
//        model.addAttribute(
//                "movie",
//                this.movieService.getMoviestById(id)
//        );
//
//        return "movie-details";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteMovie(
//            @PathVariable(value = "id") int id) {
//
//        this.movieService.deleteMovies(id);
//
//        return "redirect:/movies";
//    }
//}


@Controller
@RequestMapping("/staff/movies")
public class MovieController {

    @Autowired
    private MoviesService movieService;

    @Autowired
    private CategoryServices cateService;

    @GetMapping
    public String movieManage(Model model) {

        model.addAttribute("movie", new Movies());

        model.addAttribute(
                "movies",
                this.movieService.getMovies(null)
        );

        model.addAttribute(
                "categories",
                this.cateService.getCates()
        );

        return "movies";
    }

    @GetMapping("/{id}")
    public String updateView(
            Model model,
            @PathVariable(value = "id") int id) {

        model.addAttribute(
                "movie",
                this.movieService.getMoviestById(id)
        );

        model.addAttribute(
                "movies",
                this.movieService.getMovies(null)
        );

        model.addAttribute(
                "categories",
                this.cateService.getCates()
        );

        return "movies";
    }

    @PostMapping
    public String addMovie(
            @ModelAttribute(value = "movie") Movies movie) {

        this.movieService.addOrUpdateMovies(movie);

        return "redirect:/staff/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(
            @PathVariable(value = "id") int id) {

        this.movieService.deleteMovies(id);

        return "redirect:/staff/movies";
    }
}