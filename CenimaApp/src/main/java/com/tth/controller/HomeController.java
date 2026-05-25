/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.tth.pojo.Movies;
import com.tth.pojo.Users;
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
import com.tth.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Autowired
    private UserService userService;

    /*
        GLOBAL DATA
     */
    @ModelAttribute
    public void commonAttr(Model model,
            Principal principal) {

        model.addAttribute(
                "categories",
                cateService.getCates());

        if (principal != null) {

            Users user = userService
                    .getUserByUsername(principal.getName());

            model.addAttribute("currentUser", user);
        }
    }

    /*
        HOME
     */
    @GetMapping("/")
    public String index(
            Model model,
            @RequestParam(value = "kw", required = false) String kw,
            @RequestParam(value = "cateId", required = false) Integer cateId,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        List<Movies> movies =
                movieService.getMovies(kw, cateId, page);

        model.addAttribute("movies", movies);

        model.addAttribute("currentPage", page);

        return "index";
    }

    /*
        ROLE CHECK
     */
    @ModelAttribute("isAdmin")
    public boolean isAdmin() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        return auth != null
                && auth.getAuthorities()
                        .stream()
                        .anyMatch(a ->
                                a.getAuthority()
                                        .equals("ROLE_ADMIN"));
    }

    @ModelAttribute("isStaff")
    public boolean isStaff() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        return auth != null
                && auth.getAuthorities()
                        .stream()
                        .anyMatch(a ->
                                a.getAuthority()
                                        .equals("ROLE_STAFF"));
    }
}


//@Controller
//@ControllerAdvice
//public class HomeController {
//
//    @Autowired
//    private CategoryServices cateService;
//
//    @Autowired
//    private MoviesService movieService;
//
//    @Autowired
//    private UserService userService;
//
//    /*
//        =========================
//        LOAD GLOBAL DATA
//        =========================
//     */
//    @ModelAttribute
//    public void commonAttr(Model model,
//            Principal principal) {
//
//        model.addAttribute(
//                "categories",
//                this.cateService.getCates()
//        );
//
//        if (principal != null) {
//
//            Users user = this.userService
//                    .getUserByUsername(principal.getName());
//
//            model.addAttribute(
//                    "currentUser",
//                    user
//            );
//        }
//    }
//
//    /*
//        =========================
//        HOME PAGE
//        =========================
//     */
//    @GetMapping("/")
//    public String index(
//            Model model,
//            @RequestParam(value="kw",required = false) String kw,
//            @RequestParam(value="page",defaultValue = "1") int page) {
//
//        List<Movies> movies =
//                this.movieService.getMovies(kw, page,1);
//
//        model.addAttribute("movies", movies);
//
//        model.addAttribute("currentPage", page);
//
//        return "index";
//    }
//
////    /*
////        =========================
////        MOVIES PAGE
////        =========================
////     */
////    @GetMapping("/movies")
////    public String movies(
////            Model model,
////            @RequestParam(required = false) String kw,
////            @RequestParam(defaultValue = "1") int page) {
////
////        List<Movies> movies =
////                this.movieService.getMovies(kw, page);
////
////        model.addAttribute("movies", movies);
////
////        model.addAttribute("currentPage", page);
////
////        model.addAttribute("kw", kw);
////
////        return "movies";
////    }
//
//    /*
//        =========================
//        LOGIN PAGE
//        =========================
//     */
////    @GetMapping("/login")
////    public String login() {
////        return "login";
////    }
//
//    /*
//        =========================
//        REGISTER PAGE
//        =========================
//     */
////    @GetMapping("/register")
////    public String register(Model model) {
////
////        model.addAttribute(
////                "user",
////                new Users()
////        );
////
////        return "register";
////    }
//
//    /*
//        =========================
//        ADMIN DASHBOARD
//        =========================
//     */
//    @GetMapping("/admin")
//    public String admin(Model model) {
//
//        model.addAttribute(
//                "movies",
//                this.movieService.getMovieById(1)
//        );
//
//        return "admin-dashboard";
//    }
//
//    /*
//        =========================
//        ACCESS DENIED
//        =========================
//     */
//    @GetMapping("/403")
//    public String accessDenied() {
//        return "403";
//    }
//
//    /*
//        =========================
//        ROLE CHECK
//        =========================
//     */
//    @ModelAttribute("isAdmin")
//    public boolean isAdmin() {
//
//        Authentication auth =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();
//
//        return auth != null
//                && auth.getAuthorities()
//                        .stream()
//                        .anyMatch(a ->
//                                a.getAuthority()
//                                        .equals("ROLE_ADMIN"));
//    }
//
//    @ModelAttribute("isStaff")
//    public boolean isStaff() {
//
//        Authentication auth =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();
//
//        return auth != null
//                && auth.getAuthorities()
//                        .stream()
//                        .anyMatch(a ->
//                                a.getAuthority()
//                                        .equals("ROLE_STAFF"));
//    }
//
//    @ModelAttribute("isCustomer")
//    public boolean isCustomer() {
//
//        Authentication auth =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();
//
//        return auth != null
//                && auth.getAuthorities()
//                        .stream()
//                        .anyMatch(a ->
//                                a.getAuthority()
//                                        .equals("ROLE_CUSTOMER"));
//    }
//}