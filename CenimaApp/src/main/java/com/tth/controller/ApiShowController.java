/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controller;

import com.tth.pojo.Showtimes;
import com.tth.service.ShowtimeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api")
@CrossOrigin
public class ApiShowController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/movies/{movieId}/showtimes")
public ResponseEntity<List<Map<String, Object>>> getShowtimesByMovie(
        @PathVariable("movieId") int movieId) {

    Map<String, String> params = new HashMap<>();
    params.put("movieId", String.valueOf(movieId));

    List<Showtimes> showtimes = showtimeService.getShowtimes(params);

    List<Map<String, Object>> result = showtimes.stream().map(s -> {
        Map<String, Object> item = new HashMap<>();

        item.put("id", s.getId()); // ✅ QUAN TRỌNG

        item.put("startTime", s.getStartTime()); // ✅ FIX INVALID DATE
        item.put("endTime", s.getEndTime());

        item.put("room", Map.of(
                "id", s.getRoomId().getId(),
                "name", s.getRoomId().getName(),
                "cinema", Map.of(
                        "id", s.getRoomId().getCinemaId().getId(),
                        "name", s.getRoomId().getCinemaId().getName()
                )
        ));

        return item;
    }).toList();

    return ResponseEntity.ok(result);
}
}
