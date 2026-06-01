/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.pojo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import jakarta.persistence.UniqueConstraint;
/**
 *
 * @author Admin
 */
@Entity
@Table(
    name = "seat_showtime_status",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "showtime_id",
                "seat_id"
            }
        )
    }
)
public class SeatShowtimeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(
        name = "showtime_id",
        nullable = false
    )
    private Showtimes showtimeId;

    @ManyToOne
    @JoinColumn(
        name = "seat_id",
        nullable = false
    )
    private Seats seatId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @Column(nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lock_time")
    private Date lockTime;

    public SeatShowtimeStatus() {
    }

    public SeatShowtimeStatus(Integer id, Showtimes showtimeId, Seats seatId, Users userId, String status, Date lockTime) {
        this.id = id;
        this.showtimeId = showtimeId;
        this.seatId = seatId;
        this.userId = userId;
        this.status = status;
        this.lockTime = lockTime;
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
     * @return the showtimeId
     */
    public Showtimes getShowtimeId() {
        return showtimeId;
    }

    /**
     * @param showtimeId the showtimeId to set
     */
    public void setShowtimeId(Showtimes showtimeId) {
        this.showtimeId = showtimeId;
    }

    /**
     * @return the seatId
     */
    public Seats getSeatId() {
        return seatId;
    }

    /**
     * @param seatId the seatId to set
     */
    public void setSeatId(Seats seatId) {
        this.seatId = seatId;
    }

    /**
     * @return the userId
     */
    public Users getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Users userId) {
        this.userId = userId;
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

    /**
     * @return the lockTime
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * @param lockTime the lockTime to set
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    
}
