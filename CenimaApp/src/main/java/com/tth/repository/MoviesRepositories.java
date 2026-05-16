/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tth.repository;

import com.tth.pojo.Movies;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Administrator
 */
public interface MoviesRepositories {
    List<Movies> getMovies(Map<String, String> params);
    void addOrUpdateMovies(Movies p);
    Movies getMovieById(int id);
    void deleteMovie(int id);
}
