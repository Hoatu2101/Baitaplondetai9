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
//public interface DashboardRepository {
//    long countMovies();
//
//    long countRooms();
//
//    long countShowtimes();
//
//    long countBookings();
//
//    double totalRevenue();
//
//    List<Object[]> revenueByMovie();
//    
//    List<Object[]> revenueByShowtime();
//
//    List<Object[]> bookingByDate();
//}
public interface DashboardRepository {

    List<Object[]> statsRevenueByMovie();

    List<Object[]> statsRevenueByMonth(
            Integer year);

    List<Object[]> statsRevenueByQuarter(
            Integer year);

    List<Object[]> statsRevenueByYear();

    Long countSoldTickets();

    Double totalRevenue();

    Long countMovies();

    Long countRooms();

    Long countShowtimes();

    Long countBookings();
    
    List<Object[]> topMovies();
    
    List<Object[]> topCustomers();
}
