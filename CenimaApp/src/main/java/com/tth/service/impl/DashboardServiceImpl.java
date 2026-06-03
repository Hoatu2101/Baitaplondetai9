/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

/**
 *
 * @author Administrator
 */
import com.tth.dto.StaffDashboardDTO;
import com.tth.repository.DashboardRepository;
import com.tth.service.DashboardService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private SessionFactory factory;

    @Autowired
    private DashboardRepository dashboardRepo;

    @Override
    public List<Object[]> revenueByMovie() {
        return dashboardRepo.statsRevenueByMovie();
    }

    @Override
    public List<Object[]> revenueByMonth(
            Integer year) {

        return dashboardRepo
                .statsRevenueByMonth(year);
    }

    @Override
    public List<Object[]> revenueByQuarter(
            Integer year) {

        return dashboardRepo
                .statsRevenueByQuarter(year);
    }

    @Override
    public List<Object[]> revenueByYear() {
        return dashboardRepo
                .statsRevenueByYear();
    }

    @Override
    public Long countSoldTickets() {
        return dashboardRepo
                .countSoldTickets();
    }

    @Override
    public Double totalRevenue() {
        return dashboardRepo
                .totalRevenue();
    }

    @Override
    public Long countMovies() {
        return dashboardRepo.countMovies();
    }

    @Override
    public Long countRooms() {
        return dashboardRepo.countRooms();
    }

    @Override
    public Long countShowtimes() {
        return dashboardRepo.countShowtimes();
    }

    @Override
    public Long countBookings() {
        return dashboardRepo.countBookings();
    }

    @Override
    public List<Object[]> topMovies() {
        return dashboardRepo.topMovies();
    }

    @Override
    public StaffDashboardDTO getStaffDashboard() {

        Session s = this.factory.getCurrentSession();

        Long soldToday
                = s.createQuery("""
                SELECT COUNT(t)
                FROM Tickets t
                WHERE DATE(t.createdAt)
                    = CURRENT_DATE
            """, Long.class)
                        .getSingleResult();

        Double revenueToday
                = s.createQuery("""
                SELECT COALESCE(
                    SUM(b.totalPrice),
                    0)
                FROM Bookings b
                WHERE DATE(b.createdAt)
                    = CURRENT_DATE
            """, Double.class)
                        .getSingleResult();

        Long showtimesToday
                = s.createQuery("""
                SELECT COUNT(s)
                FROM Showtimes s
                WHERE DATE(s.startTime)
                    = CURRENT_DATE
            """, Long.class)
                        .getSingleResult();

        Long running
                = s.createQuery("""
                SELECT COUNT(s)
                FROM Showtimes s
                WHERE CURRENT_TIMESTAMP
                    BETWEEN s.startTime
                    AND s.endTime
            """, Long.class)
                        .getSingleResult();

        Long freeSeats
                = s.createQuery("""
                SELECT COUNT(se)
                FROM Seats se
                WHERE se.id NOT IN (
                    SELECT sts.seatId.id
                    FROM SeatShowtimeStatus sts
                    WHERE sts.status='BOOKED'
                )
            """, Long.class)
                        .getSingleResult();

        return new StaffDashboardDTO(
                soldToday,
                revenueToday,
                showtimesToday,
                running,
                freeSeats
        );
    }

    @Override
    public List<Object[]> revenueByDate(
            Date fromDate,
            Date toDate) {

        if (fromDate == null || toDate == null) {
            return new ArrayList<>();
        }

        return dashboardRepo
                .statsRevenueByDate(
                        fromDate,
                        toDate);
    }

    @Override
    public List<Object[]> topCustomers() {
        return dashboardRepo.topCustomers();
    }

}
