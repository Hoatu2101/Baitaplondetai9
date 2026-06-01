/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */
import java.util.List;

//public interface DashboardService {
//
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
//    List<Object[]> bookingByDate();
//}
public interface DashboardService {

    List<Object[]> revenueByMovie();

    List<Object[]> revenueByMonth(
            Integer year);

    List<Object[]> revenueByQuarter(
            Integer year);

    List<Object[]> revenueByYear();

    Long countSoldTickets();

    Double totalRevenue();

    Long countMovies();

    Long countRooms();

    Long countShowtimes();

    Long countBookings();
    
    List<Object[]> topMovies();
}
