/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BookingDetailResponse {

    private Integer bookingId;

    private String customerName;

    private String movieName;

    private List<String> seats;

    private float totalPrice;

    private Date createdAt;

    public BookingDetailResponse() {
    }

    public BookingDetailResponse(Integer bookingId, String customerName, String movieName, List<String> seats, float totalPrice, Date createdAt) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    /**
     * @return the bookingId
     */
    public Integer getBookingId() {
        return bookingId;
    }

    /**
     * @param bookingId the bookingId to set
     */
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the movieName
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * @param movieName the movieName to set
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * @return the seats
     */
    public List<String> getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    

}
