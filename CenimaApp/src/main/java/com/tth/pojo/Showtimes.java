/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "showtimes")
public class Showtimes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movies movieId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Rooms roomId;

    @OneToMany(mappedBy = "showtimeId")
    private List<Bookings> bookingsList;

    @OneToMany(mappedBy = "showtimeId")
    private List<SeatShowtimeStatus> seatStatuses;

    @PrePersist
    public void prePersist() {
        setCreatedAt(new Date());
    }

    public Showtimes() {
    }

    public Showtimes(Integer id, Date startTime, Date endTime, Date createdAt, Movies movieId, Rooms roomId, List<Bookings> bookingsList) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.movieId = movieId;
        this.roomId = roomId;
        this.bookingsList = bookingsList;
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
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    /**
     * @return the movieId
     */
    public Movies getMovieId() {
        return movieId;
    }

    /**
     * @param movieId the movieId to set
     */
    public void setMovieId(Movies movieId) {
        this.movieId = movieId;
    }

    /**
     * @return the roomId
     */
    public Rooms getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(Rooms roomId) {
        this.roomId = roomId;
    }

    /**
     * @return the bookingsList
     */
    public List<Bookings> getBookingsList() {
        return bookingsList;
    }

    /**
     * @param bookingsList the bookingsList to set
     */
    public void setBookingsList(List<Bookings> bookingsList) {
        this.bookingsList = bookingsList;
    }

    /**
     * @return the seatStatuses
     */
    public List<SeatShowtimeStatus> getSeatStatuses() {
        return seatStatuses;
    }

    /**
     * @param seatStatuses the seatStatuses to set
     */
    public void setSeatStatuses(List<SeatShowtimeStatus> seatStatuses) {
        this.seatStatuses = seatStatuses;
    }

}
