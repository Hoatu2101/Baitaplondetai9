/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */
import com.tth.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiMovieController {

    @Autowired
    private MoviesService movieService;

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(
            @PathVariable(value = "id") int id) {

        try {

            movieService.deleteMovie(id);

            return new ResponseEntity<>(
                    "Delete success",
                    HttpStatus.OK);

        } catch (Exception ex) {

            ex.printStackTrace();

            return new ResponseEntity<>(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
