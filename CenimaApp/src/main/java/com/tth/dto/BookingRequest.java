/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.dto;

/**
 *
 * @author Admin
 */
public class BookingRequest {

    private Integer showtimeId;

    private Integer seatId;

    public Integer getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(
            Integer showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(
            Integer seatId) {
        this.seatId = seatId;
    }
}
