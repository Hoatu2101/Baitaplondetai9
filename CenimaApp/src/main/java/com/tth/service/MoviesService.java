/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Administrator
 */
import com.tth.pojo.Movies;
import java.util.List;
import java.util.Map;
public interface MoviesService {
    List<Movies> getMovies(Map<String, String> params);
    void addOrUpdateMovies(Movies m);
    Movies getMoviestById(int id);
    void deleteMovies(int id);
}
