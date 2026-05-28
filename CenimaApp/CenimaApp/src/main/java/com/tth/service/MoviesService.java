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

    List<Movies> getMovies(String kw, Integer cateId, int page);
    List<Movies> getMovies(Map<String, String> params);
    Movies getMovieById(int id);

    void addOrUpdate(Movies movie);

    void deleteMovie(int id);
}