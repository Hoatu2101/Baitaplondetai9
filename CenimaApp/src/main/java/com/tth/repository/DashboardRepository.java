/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository;

import java.util.List;

/**
 *
 * @author Admin
 */


public interface DashboardRepository {

    long countMovies();

    long countRooms();

    long countShowtimes();

    long countBookings();

    double totalRevenue();

    List<Object[]> revenueByMovie();

    List<Object[]> bookingByDate();
}
