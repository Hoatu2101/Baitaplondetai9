/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.tth.pojo.Users;
import com.tth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Admin
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerView(Model model) {

        model.addAttribute("user", new Users());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(value = "user") Users user) {

        this.userService.addUser(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/admin/pending-staff")
    public String pendingStaff(Model model) {

        model.addAttribute(
                "users",
                this.userService.getPendingStaff()
        );

        return "pending-staff";
    }

    @GetMapping("/admin/approve/{id}")
    public String approve(@PathVariable(value = "id") int id) {

        this.userService.approveStaff(id);

        return "redirect:/admin/pending-staff";
    }
}