/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;


/**
 *
 * @author Admin
 */

import com.tth.pojo.Cinemas;
import com.tth.repository.CinemaRepository;
import com.tth.service.CinemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepo;

    @Override
    public List<Cinemas> getCinemas() {
        return this.cinemaRepo.getCinemas();
    }

    @Override
    public Cinemas getCinemaById(int id) {
        return this.cinemaRepo.getCinemaById(id);
    }
}