/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.dto;

/**
 *
 * @author Admin
 */

public class SeatStatusResponse {

    private Integer seatId;

    private String seatNumber;

    private String status;

    public SeatStatusResponse() {
    }

    public SeatStatusResponse(
            Integer seatId,
            String seatNumber,
            String status) {

        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    /**
     * @return the seatId
     */
    public Integer getSeatId() {
        return seatId;
    }

    /**
     * @param seatId the seatId to set
     */
    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    /**
     * @return the seatNumber
     */
    public String getSeatNumber() {
        return seatNumber;
    }

    /**
     * @param seatNumber the seatNumber to set
     */
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
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