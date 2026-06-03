package com.tth.dto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class StaffDashboardDTO {

    private long totalTicketsToday;

    private double revenueToday;

    private long totalShowtimesToday;

    private long runningShowtimes;

    private long availableSeats;

    public StaffDashboardDTO() {
    }

    public StaffDashboardDTO(
            long totalTicketsToday,
            double revenueToday,
            long totalShowtimesToday,
            long runningShowtimes,
            long availableSeats) {

        this.totalTicketsToday =
                totalTicketsToday;

        this.revenueToday =
                revenueToday;

        this.totalShowtimesToday =
                totalShowtimesToday;

        this.runningShowtimes =
                runningShowtimes;

        this.availableSeats =
                availableSeats;
    }


    /**
     * @return the totalTicketsToday
     */
    public long getTotalTicketsToday() {
        return totalTicketsToday;
    }

    /**
     * @param totalTicketsToday the totalTicketsToday to set
     */
    public void setTotalTicketsToday(long totalTicketsToday) {
        this.totalTicketsToday = totalTicketsToday;
    }

    /**
     * @return the revenueToday
     */
    public double getRevenueToday() {
        return revenueToday;
    }

    /**
     * @param revenueToday the revenueToday to set
     */
    public void setRevenueToday(double revenueToday) {
        this.revenueToday = revenueToday;
    }

    /**
     * @return the totalShowtimesToday
     */
    public long getTotalShowtimesToday() {
        return totalShowtimesToday;
    }

    /**
     * @param totalShowtimesToday the totalShowtimesToday to set
     */
    public void setTotalShowtimesToday(long totalShowtimesToday) {
        this.totalShowtimesToday = totalShowtimesToday;
    }

    /**
     * @return the runningShowtimes
     */
    public long getRunningShowtimes() {
        return runningShowtimes;
    }

    /**
     * @param runningShowtimes the runningShowtimes to set
     */
    public void setRunningShowtimes(long runningShowtimes) {
        this.runningShowtimes = runningShowtimes;
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
    
}