/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

import com.tth.pojo.Categories;
import com.tth.repository.CategoryRepositories;

import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */

import com.tth.repository.DashboardRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DashBoardRepositoryImpl implements DashboardRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public long countMovies() {

        Session s = this.factory.getCurrentSession();

        String hql = "SELECT COUNT(m.id) FROM Movies m";

        return (Long) s.createQuery(hql).uniqueResult();
    }

    @Override
    public long countRooms() {

        Session s = this.factory.getCurrentSession();

        String hql = "SELECT COUNT(r.id) FROM Rooms r";

        return (Long) s.createQuery(hql).uniqueResult();
    }

    @Override
    public long countShowtimes() {

        Session s = this.factory.getCurrentSession();

        String hql = "SELECT COUNT(st.id) FROM Showtimes st";

        return (Long) s.createQuery(hql).uniqueResult();
    }

    @Override
    public long countBookings() {

        Session s = this.factory.getCurrentSession();

        String hql = "SELECT COUNT(b.id) FROM Bookings b";

        return (Long) s.createQuery(hql).uniqueResult();
    }

    @Override
    public double totalRevenue() {

        Session s = this.factory.getCurrentSession();

        String hql = "SELECT SUM(b.totalPrice) FROM Bookings b";

        Double total = (Double) s.createQuery(hql).uniqueResult();

        return total == null ? 0 : total;
    }

    @Override
    public List<Object[]> revenueByMovie() {

        Session s = this.factory.getCurrentSession();

        String hql = """
            SELECT st.movieId.movieName, SUM(b.totalPrice)
            FROM Bookings b
            JOIN b.showtimeId st
            GROUP BY st.movieId.movieName
        """;

        return s.createQuery(hql, Object[].class).getResultList();
    }

    @Override
    public List<Object[]> bookingByDate() {

        Session s = this.factory.getCurrentSession();

        String hql = """
            SELECT DATE(b.createdAt), COUNT(b.id)
            FROM Bookings b
            GROUP BY DATE(b.createdAt)
            ORDER BY DATE(b.createdAt)
        """;

        return s.createQuery(hql, Object[].class).getResultList();
    }
}