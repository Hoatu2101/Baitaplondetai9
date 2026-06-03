package com.tth.dto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */

public class ShowtimeAdminDTO {

    private Integer id;

    private String movieName;

    private String roomName;

    private int totalSeats;

    private long soldSeats;

    private long availableSeats;

    private double occupancyRate;

    private double revenue;

    private String status;

    public ShowtimeAdminDTO() {
    }

    public ShowtimeAdminDTO(
            Integer id,
            String movieName,
            String roomName,
            int totalSeats,
            long soldSeats,
            long availableSeats,
            double occupancyRate,
            double revenue,
            String status) {

        this.id = id;
        this.movieName = movieName;
        this.roomName = roomName;
        this.totalSeats = totalSeats;
        this.soldSeats = soldSeats;
        this.availableSeats = availableSeats;
        this.occupancyRate = occupancyRate;
        this.revenue = revenue;
        this.status = status;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the totalSeats
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * @param totalSeats the totalSeats to set
     */
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    /**
     * @return the soldSeats
     */
    public long getSoldSeats() {
        return soldSeats;
    }

    /**
     * @param soldSeats the soldSeats to set
     */
    public void setSoldSeats(long soldSeats) {
        this.soldSeats = soldSeats;
    }

    /**
     * @return the availableSeats
     */
    public long getAvailableSeats() {
        return availableSeats;
    }

    /**
     * @param availableSeats the availableSeats to set
     */
    public void setAvailableSeats(long availableSeats) {
        this.availableSeats = availableSeats;
    }

    /**
     * @return the occupancyRate
     */
    public double getOccupancyRate() {
        return occupancyRate;
    }

    /**
     * @param occupancyRate the occupancyRate to set
     */
    public void setOccupancyRate(double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    /**
     * @return the revenue
     */
    public double getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue to set
     */
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
}