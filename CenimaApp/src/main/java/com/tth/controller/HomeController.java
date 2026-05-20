/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.tth.service.CategoryServices;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tth.service.MoviesService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author admin
 */
@Controller
@ControllerAdvice
public class HomeController {

    @Autowired
    private CategoryServices cateService;

    @Autowired
    private MoviesService movieService;

//    @ModelAttribute
//    public void commonResponses(Model model) {
//        model.addAttribute("categories", this.cateService.getCates());
//    }
//    @RequestMapping("/")
//    public String index(Model model, @RequestParam Map<String, String> params) {
//        
//        model.addAttribute("movies", this.MovieService.getMovies(params));
//        return "index";
//    }
    @GetMapping("/")
    public String index(Model model,
            @RequestParam Map<String, String> params) {

        model.addAttribute(
                "movies",
                this.movieService.getMovies(params)
        );

        model.addAttribute(
                "categories",
                this.cateService.getCates()
        );

        return "index";
    }

}
