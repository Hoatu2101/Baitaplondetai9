/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

/**
 *
 * @author Administrator
 */

import com.tth.repository.DashboardRepository;
import com.tth.service.DashboardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepo;

    @Override
    public long countMovies() {
        return this.dashboardRepo.countMovies();
    }

    @Override
    public long countRooms() {
        return this.dashboardRepo.countRooms();
    }

    @Override
    public long countShowtimes() {
        return this.dashboardRepo.countShowtimes();
    }

    @Override
    public long countBookings() {
        return this.dashboardRepo.countBookings();
    }

    @Override
    public double totalRevenue() {
        return this.dashboardRepo.totalRevenue();
    }

    @Override
    public List<Object[]> revenueByMovie() {
        return this.dashboardRepo.revenueByMovie();
    }

    @Override
    public List<Object[]> bookingByDate() {
        return this.dashboardRepo.bookingByDate();
    }
}
