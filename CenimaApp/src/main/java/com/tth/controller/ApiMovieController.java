/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

/**
 *
 * @author Admin
 */
import com.tth.pojo.Movies;
import com.tth.service.MoviesService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     @GetMapping("/movies/{id}")
public ResponseEntity<Movies> retrieve(@PathVariable(value = "id") int id) {
    return new ResponseEntity<>(
            this.movieService.getMovieById(id),
            HttpStatus.OK);
}
   @GetMapping("/movies")
public ResponseEntity<List<Map<String,Object>>> list(
        @RequestParam Map<String, String> params) {

    List<Movies> movies = this.movieService.getMovies(params);

    List<Map<String, Object>> result = movies.stream().map(m -> {
        Map<String, Object> data = new HashMap<>();

        data.put("id", m.getId());
        data.put("movieName", m.getMovieName());
        data.put("duration", m.getDuration());
        data.put("category", m.getCategory().getName());
        data.put("poster", m.getPoster());

        return data;
    }).toList();

    return new ResponseEntity<>(result, HttpStatus.OK);
}

}
