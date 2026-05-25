/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tth.repository;

import com.tth.pojo.Movies;
import java.util.List;
/**
 *
 * @author Administrator
 */
public interface MoviesRepositories {

    List<Movies> getMovies(String kw, Integer cateId, int page);

    Movies getMovieById(int id);

    void addOrUpdate(Movies movie);

    void deleteMovie(int id);
}
