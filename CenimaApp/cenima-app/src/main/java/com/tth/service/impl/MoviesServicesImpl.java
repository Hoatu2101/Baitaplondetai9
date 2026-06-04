/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tth.pojo.Movies;
import com.tth.repository.MoviesRepositories;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tth.service.MoviesService;
import java.io.IOException;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */

@Service
@Transactional
public class MoviesServicesImpl implements MoviesService {

    @Autowired
    private MoviesRepositories movieRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Movies> getMovies(Map<String, String> params) {
        return this.movieRepo.getMovies(params);
    }

    @Override
    public Movies getMovieById(int id) {
        return this.movieRepo.getMovieById(id);
    }

    @Override
    public void addOrUpdate(Movies movie) {

        if (!movie.getFile().isEmpty()) {

            try {

                Map res = cloudinary.uploader().upload(
                        movie.getFile().getBytes(),
                        ObjectUtils.asMap(
                                "resource_type",
                                "auto"
                        )
                );

                movie.setPoster(res.get("secure_url").toString());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        this.movieRepo.addOrUpdate(movie);
    }

    @Override
    public void deleteMovie(int id) {
        this.movieRepo.deleteMovie(id);
    }

    @Override
    public long countMovies() {
        return this.movieRepo.countMovies();
    }
}