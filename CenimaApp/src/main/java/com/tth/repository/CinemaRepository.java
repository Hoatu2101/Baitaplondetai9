package com.tth.repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */


import com.tth.pojo.Cinemas;
import java.util.List;

public interface CinemaRepository {
    List<Cinemas> getCinemas();
    Cinemas getCinemaById(int id);
}