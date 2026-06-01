/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

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

//    @Override
//    public long countMovies() {
//
//        Session s = this.factory.getCurrentSession();
//
//        String hql = "SELECT COUNT(m.id) FROM Movies m";
//
//        return (Long) s.createQuery(hql).uniqueResult();
//    }
//
//    @Override
//    public long countRooms() {
//
//        Session s = this.factory.getCurrentSession();
//
//        String hql = "SELECT COUNT(r.id) FROM Rooms r";
//
//        return (Long) s.createQuery(hql).uniqueResult();
//    }
//
//    @Override
//    public long countShowtimes() {
//
//        Session s = this.factory.getCurrentSession();
//
//        String hql = "SELECT COUNT(st.id) FROM Showtimes st";
//
//        return (Long) s.createQuery(hql).uniqueResult();
//    }
//
//    @Override
//    public long countBookings() {
//
//        Session s = this.factory.getCurrentSession();
//
//        String hql = "SELECT COUNT(b.id) FROM Bookings b";
//
//        return (Long) s.createQuery(hql).uniqueResult();
//    }
//
//    @Override
//    public double totalRevenue() {
//
//        Session s = this.factory.getCurrentSession();
//
//        String hql = "SELECT SUM(b.totalPrice) FROM Bookings b";
//
//        Double total = (Double) s.createQuery(hql).uniqueResult();
//
//        return total == null ? 0 : total;
//    }
//
//    @Override
//    public List<Object[]> revenueByMovie() {
//
//        Session s = this.factory.getCurrentSession();
//
//        String hql = """
//            SELECT st.movieId.movieName, SUM(b.totalPrice)
//            FROM Bookings b
//            JOIN b.showtimeId st
//            GROUP BY st.movieId.movieName
//        """;
//
//        return s.createQuery(hql, Object[].class).getResultList();
//    }
//
//    @Override
//    public List<Object[]> revenueByShowtime() {
//
//        Session s = factory.getCurrentSession();
//
//        String hql = """
//        SELECT
//            st.id,
//            st.startTime,
//            SUM(b.totalPrice)
//        FROM Bookings b
//        JOIN b.showtimeId st
//        GROUP BY st.id, st.startTime
//        ORDER BY st.startTime DESC
//    """;
//
//        return s.createQuery(
//                hql,
//                Object[].class
//        ).getResultList();
//    }
//
//    @Override
//    public List<Object[]> bookingByDate() {
//
//        Session s = this.factory.getCurrentSession();
//
//        String hql = """
//            SELECT DATE(b.createdAt), COUNT(b.id)
//            FROM Bookings b
//            GROUP BY DATE(b.createdAt)
//            ORDER BY DATE(b.createdAt)
//        """;
//
//        return s.createQuery(hql, Object[].class).getResultList();
//    }
    @Override
    public List<Object[]> statsRevenueByMovie() {

        Session s = factory.getCurrentSession();

        String hql = """
        SELECT m.movieName,
               SUM(t.price)
        FROM Tickets t
        JOIN t.bookingId b
        JOIN b.showtimeId st
        JOIN st.movieId m
        GROUP BY m.id, m.movieName
        ORDER BY SUM(t.price) DESC
    """;

        return s.createQuery(
                hql,
                Object[].class)
                .getResultList();
    }

    @Override
    public List<Object[]> statsRevenueByMonth(
            Integer year) {

        Session s = factory.getCurrentSession();

        String hql = """
        SELECT MONTH(b.createdAt),
               SUM(t.price)
        FROM Tickets t
        JOIN t.bookingId b
        WHERE YEAR(b.createdAt)=:year
        GROUP BY MONTH(b.createdAt)
        ORDER BY MONTH(b.createdAt)
    """;

        return s.createQuery(hql, Object[].class)
                .setParameter("year", year)
                .getResultList();
    }

    @Override
    public List<Object[]> statsRevenueByQuarter(
            Integer year) {

        Session s = factory.getCurrentSession();

        String hql = """
        SELECT QUARTER(b.createdAt),
               SUM(t.price)
        FROM Tickets t
        JOIN t.bookingId b
        WHERE YEAR(b.createdAt)=:year
        GROUP BY QUARTER(b.createdAt)
        ORDER BY QUARTER(b.createdAt)
    """;

        return s.createQuery(hql, Object[].class)
                .setParameter("year", year)
                .getResultList();
    }

    @Override
    public List<Object[]> statsRevenueByYear() {

        Session s = factory.getCurrentSession();

        String hql = """
        SELECT YEAR(b.createdAt),
               SUM(t.price)
        FROM Tickets t
        JOIN t.bookingId b
        GROUP BY YEAR(b.createdAt)
        ORDER BY YEAR(b.createdAt)
    """;

        return s.createQuery(hql, Object[].class)
                .getResultList();
    }

    @Override
    public Long countSoldTickets() {

        Session s = factory.getCurrentSession();

        return s.createQuery("""
        SELECT COUNT(t)
        FROM Tickets t
    """, Long.class)
                .getSingleResult();
    }

    @Override
    public Double totalRevenue() {

        Session s = factory.getCurrentSession();

        Double revenue = s.createQuery("""
        SELECT SUM(t.price)
        FROM Tickets t
    """, Double.class)
                .getSingleResult();

        return revenue == null
                ? 0D
                : revenue;
    }

    @Override
    public Long countMovies() {

        return factory
                .getCurrentSession()
                .createQuery(
                        "SELECT COUNT(m.id) FROM Movies m",
                        Long.class)
                .getSingleResult();
    }

    @Override
    public Long countRooms() {

        return factory
                .getCurrentSession()
                .createQuery(
                        "SELECT COUNT(r.id) FROM Rooms r",
                        Long.class)
                .getSingleResult();
    }

    @Override
    public Long countShowtimes() {

        return factory
                .getCurrentSession()
                .createQuery(
                        "SELECT COUNT(s.id) FROM Showtimes s",
                        Long.class)
                .getSingleResult();
    }

    @Override
    public Long countBookings() {

        return factory
                .getCurrentSession()
                .createQuery(
                        "SELECT COUNT(b.id) FROM Bookings b",
                        Long.class)
                .getSingleResult();
    }

    @Override
    public List<Object[]> topMovies() {

        String hql = """
        SELECT m.movieName,
               COUNT(t.id)
        FROM Tickets t
        JOIN t.bookingId b
        JOIN b.showtimeId st
        JOIN st.movieId m
        GROUP BY m.id,m.movieName
        ORDER BY COUNT(t.id) DESC
    """;

        return factory
                .getCurrentSession()
                .createQuery(
                        hql,
                        Object[].class)
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public List<Object[]> topCustomers() {

        String hql = """
        SELECT u.fullName,
               COUNT(t.id)
        FROM Tickets t
        JOIN t.bookingId b
        JOIN b.userId u
        GROUP BY u.id,u.fullName
        ORDER BY COUNT(t.id) DESC
    """;

        return factory
                .getCurrentSession()
                .createQuery(
                        hql,
                        Object[].class)
                .setMaxResults(10)
                .getResultList();
    }
}
