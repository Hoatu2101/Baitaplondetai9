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
//@Entity
//@Table(name = "rooms")
//@NamedQueries({
//    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
//    @NamedQuery(name = "Rooms.findByName", query = "SELECT r FROM Rooms r WHERE r.name = :name"),
//    @NamedQuery(name = "Rooms.findByCapacity", query = "SELECT r FROM Rooms r WHERE r.capacity = :capacity"),
//    @NamedQuery(name = "Rooms.findById", query = "SELECT r FROM Rooms r WHERE r.id = :id"),
//    @NamedQuery(name = "Rooms.findByCreatedAt", query = "SELECT r FROM Rooms r WHERE r.createdAt = :createdAt")})
//public class Rooms implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 255)
//    @Column(name = "name")
//    private String name;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "capacity")
//    private int capacity;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Cinemas cinemaId;
//    @JoinColumn(name = "status_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Status statusId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
//    private List<Showtimes> showtimesList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
//    private List<Seats> seatsList;
//
//    public Rooms() {
//    }
//
//    public Rooms(Integer id) {
//        this.id = id;
//    }
//
//    public Rooms(Integer id, String name, int capacity) {
//        this.id = id;
//        this.name = name;
//        this.capacity = capacity;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getCapacity() {
//        return capacity;
//    }
//
//    public void setCapacity(int capacity) {
//        this.capacity = capacity;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Cinemas getCinemaId() {
//        return cinemaId;
//    }
//
//    public void setCinemaId(Cinemas cinemaId) {
//        this.cinemaId = cinemaId;
//    }
//
//    public Status getStatusId() {
//        return statusId;
//    }
//
//    public void setStatusId(Status statusId) {
//        this.statusId = statusId;
//    }
//
//    public List<Showtimes> getShowtimesList() {
//        return showtimesList;
//    }
//
//    public void setShowtimesList(List<Showtimes> showtimesList) {
//        this.showtimesList = showtimesList;
//    }
//
//    public List<Seats> getSeatsList() {
//        return seatsList;
//    }
//
//    public void setSeatsList(List<Seats> seatsList) {
//        this.seatsList = seatsList;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Rooms)) {
//            return false;
//        }
//        Rooms other = (Rooms) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.tth.pojo.Rooms[ id=" + id + " ]";
//    }
//    
//}

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