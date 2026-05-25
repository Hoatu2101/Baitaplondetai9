/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;


import com.tth.pojo.Movies;
import com.tth.repository.MoviesRepositories;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tth.service.MoviesService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class MoviesServicesImpl
        implements MoviesService {

    @Autowired
    private MoviesRepositories movieRepo;

    @Override
    public List<Movies> getMovies(String kw,
                                  Integer cateId,
                                  int page) {

        return movieRepo.getMovies(kw, cateId, page);
    }

    @Override
    public Movies getMovieById(int id) {
        return movieRepo.getMovieById(id);
    }

    @Override
    public void addOrUpdate(Movies movie) {
        movieRepo.addOrUpdate(movie);
    }

    @Override
    public void deleteMovie(int id) {
        movieRepo.deleteMovie(id);
    }
}