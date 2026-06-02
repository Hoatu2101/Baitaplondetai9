/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;


import com.tth.pojo.Status;
import com.tth.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/statuses")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String statuses(Model model){

        model.addAttribute(
                "statuses",
                statusService.getStatuses());

        return "statuses";
    }

    @GetMapping("/create")
    public String createForm(Model model){

        model.addAttribute(
                "status",
                new Status());

        return "status-form";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Status status){

        statusService
                .addOrUpdate(status);

        return "redirect:/admin/statuses";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Integer id){

        statusService
                .deleteStatus(id);

        return "redirect:/admin/statuses";
    }
}