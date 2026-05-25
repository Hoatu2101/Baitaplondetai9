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
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tth.service.MoviesService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
//@Service
//public class MoviesServicesImpl implements MoviesService{
//
//    
//    @Autowired
//    private MoviesRepositories moRepo;
//    
//    @Autowired
//    private Cloudinary cloudinary;
//     
//    @Override
//    public List<Movies> getMovies(Map<String, String> params) {
//            return this.moRepo.getMovies(params);
//    }
//
//
//    @Override
//    public Movies getMoviestById(int id) {
//       return this.moRepo.getMovieById(id);
//    }
//
//    @Override
//    public void deleteMovies(int id) {
//        this.moRepo.deleteMovie(id);
//    }
//
//    @Override
//    public void addOrUpdateMovies(Movies m) {
//         if (!m.getFile().isEmpty()) {
//            try {
//                Map res = this.cloudinary.uploader().upload(m.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
//                m.setPosterUrl(res.get("secure_url").toString());
//           } catch (IOException ex) {
//                Logger.getLogger(MoviesServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
//        
//        this.moRepo.addOrUpdateMovies(m);
//    }
//}

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