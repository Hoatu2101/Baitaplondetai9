/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.tth.pojo.Cinemas;
import com.tth.service.CinemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/cinemas")
@CrossOrigin
public class CinemaApiController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public ResponseEntity<List<Cinemas>> getAll() {
        return ResponseEntity.ok(cinemaService.getCinemas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinemas> getById(@PathVariable int id) {

        Cinemas c = cinemaService.getCinemaById(id);

        if (c == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(c);
    }
}