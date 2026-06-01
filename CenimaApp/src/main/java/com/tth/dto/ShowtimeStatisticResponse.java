package com.tth.dto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class ShowtimeStatisticResponse {

    private Integer showtimeId;

    private Long soldTickets;

    private Double revenue;

    public ShowtimeStatisticResponse() {
    }

    public ShowtimeStatisticResponse(
            Integer showtimeId,
            Long soldTickets,
            Double revenue) {

        this.showtimeId = showtimeId;
        this.soldTickets = soldTickets;
        this.revenue = revenue;
    }

    /**
     * @return the showtimeId
     */
    public Integer getShowtimeId() {
        return showtimeId;
    }

    /**
     * @param showtimeId the showtimeId to set
     */
    public void setShowtimeId(Integer showtimeId) {
        this.showtimeId = showtimeId;
    }

    /**
     * @return the soldTickets
     */
    public Long getSoldTickets() {
        return soldTickets;
    }

    /**
     * @param soldTickets the soldTickets to set
     */
    public void setSoldTickets(Long soldTickets) {
        this.soldTickets = soldTickets;
    }

    /**
     * @return the revenue
     */
    public Double getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue to set
     */
    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    
}
