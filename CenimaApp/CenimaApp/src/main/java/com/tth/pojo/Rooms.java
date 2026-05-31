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
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */

@Entity
@Table(name = "rooms")
public class Rooms implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinemas cinemaId;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status statusId;

    @OneToMany(mappedBy = "roomId")
    private List<Showtimes> showtimesList;

    @OneToMany(mappedBy = "roomId")
    private List<Seats> seatsList;

    @PrePersist
    public void prePersist() {
        setCreatedAt(new Date());
    }

    public Rooms() {
    }

    public Rooms(Integer id, String name, int capacity, Date createdAt, Cinemas cinemaId, Status statusId, List<Showtimes> showtimesList, List<Seats> seatsList) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.createdAt = createdAt;
        this.cinemaId = cinemaId;
        this.statusId = statusId;
        this.showtimesList = showtimesList;
        this.seatsList = seatsList;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
     * @return the cinemaId
     */
    public Cinemas getCinemaId() {
        return cinemaId;
    }

    /**
     * @param cinemaId the cinemaId to set
     */
    public void setCinemaId(Cinemas cinemaId) {
        this.cinemaId = cinemaId;
    }

    /**
     * @return the statusId
     */
    public Status getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    /**
     * @return the showtimesList
     */
    public List<Showtimes> getShowtimesList() {
        return showtimesList;
    }

    /**
     * @param showtimesList the showtimesList to set
     */
    public void setShowtimesList(List<Showtimes> showtimesList) {
        this.showtimesList = showtimesList;
    }

    /**
     * @return the seatsList
     */
    public List<Seats> getSeatsList() {
        return seatsList;
    }

    /**
     * @param seatsList the seatsList to set
     */
    public void setSeatsList(List<Seats> seatsList) {
        this.seatsList = seatsList;
    }
  
    
}