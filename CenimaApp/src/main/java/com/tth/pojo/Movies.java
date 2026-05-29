/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
//@Entity
//@Table(name = "movies")
//public class Movies implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "movie_name")
//    private String movieName;
//
//    @Column(columnDefinition = "TEXT")
//    private String description;
//
//    private String poster;
//
//    private String trailer;
//
//    private Double price;
//
//    @Column(name = "movie_format")
//    private String movieFormat;
//
//    private Integer duration;
//
//    private Boolean active = true;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_date")
//    private Date createdDate = new Date();
//
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Categories category;
//
//    @ManyToOne
//    @JoinColumn(name = "status_id")
//    private StatusMovie statusMovie;
//
//    public Movies() {
//    }
//
//    public Movies(Integer id, String movieName, String description, String poster, String trailer, Double price, String movieFormat, Integer duration, Categories category) {
//        this.id = id;
//        this.movieName = movieName;
//        this.description = description;
//        this.poster = poster;
//        this.trailer = trailer;
//        this.price = price;
//        this.movieFormat = movieFormat;
//        this.duration = duration;
//        this.category = category;
//    }
//
//    /**
//     * @return the id
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     * @param id the id to set
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    /**
//     * @return the movieName
//     */
//    public String getMovieName() {
//        return movieName;
//    }
//
//    /**
//     * @param movieName the movieName to set
//     */
//    public void setMovieName(String movieName) {
//        this.movieName = movieName;
//    }
//
//    /**
//     * @return the description
//     */
//    public String getDescription() {
//        return description;
//    }
//
//    /**
//     * @param description the description to set
//     */
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    /**
//     * @return the poster
//     */
//    public String getPoster() {
//        return poster;
//    }
//
//    /**
//     * @param poster the poster to set
//     */
//    public void setPoster(String poster) {
//        this.poster = poster;
//    }
//
//    /**
//     * @return the trailer
//     */
//    public String getTrailer() {
//        return trailer;
//    }
//
//    /**
//     * @param trailer the trailer to set
//     */
//    public void setTrailer(String trailer) {
//        this.trailer = trailer;
//    }
//
//    /**
//     * @return the price
//     */
//    public Double getPrice() {
//        return price;
//    }
//
//    /**
//     * @param price the price to set
//     */
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    /**
//     * @return the movieFormat
//     */
//    public String getMovieFormat() {
//        return movieFormat;
//    }
//
//    /**
//     * @param movieFormat the movieFormat to set
//     */
//    public void setMovieFormat(String movieFormat) {
//        this.movieFormat = movieFormat;
//    }
//
//    /**
//     * @return the duration
//     */
//    public Integer getDuration() {
//        return duration;
//    }
//
//    /**
//     * @param duration the duration to set
//     */
//    public void setDuration(Integer duration) {
//        this.duration = duration;
//    }
//
//    /**
//     * @return the active
//     */
//    public Boolean getActive() {
//        return active;
//    }
//
//    /**
//     * @param active the active to set
//     */
//    public void setActive(Boolean active) {
//        this.active = active;
//    }
//
//    /**
//     * @return the createdDate
//     */
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    /**
//     * @param createdDate the createdDate to set
//     */
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    /**
//     * @return the category
//     */
//    public Categories getCategory() {
//        return category;
//    }
//
//    /**
//     * @param category the category to set
//     */
//    public void setCategory(Categories category) {
//        this.category = category;
//    }
//
//    /**
//     * @return the statusMovie
//     */
//    public StatusMovie getStatusMovie() {
//        return statusMovie;
//    }
//
//    /**
//     * @param statusMovie the statusMovie to set
//     */
//    public void setStatusMovie(StatusMovie statusMovie) {
//        this.statusMovie = statusMovie;
//    }
//
//}
@Entity
@Table(name = "movies")
public class Movies implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @Lob
    private String description;

    private String trailer;

    private Float price;

    @Column(name = "movie_format")
    private String movieFormat;

    private Integer duration;

    private String poster;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Categories category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_movie_id")
    private StatusMovie statusMovie;

    @Transient
    private MultipartFile file;

    @OneToMany(mappedBy = "movieId")
    @JsonIgnore
    private List<Showtimes> showtimesList;

    @PrePersist
    public void prePersist() {
        setCreatedAt(new Date());
    }

    public Movies() {
    }

    public Movies(Integer id, String movieName, String description, String trailer, Float price, String movieFormat, Integer duration, String poster, Date createdAt, Categories category, MultipartFile file, List<Showtimes> showtimesList) {
        this.id = id;
        this.movieName = movieName;
        this.description = description;
        this.trailer = trailer;
        this.price = price;
        this.movieFormat = movieFormat;
        this.duration = duration;
        this.poster = poster;
        this.createdAt = createdAt;
        this.category = category;
        this.file = file;
        this.showtimesList = showtimesList;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the trailer
     */
    public String getTrailer() {
        return trailer;
    }

    /**
     * @param trailer the trailer to set
     */
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return the movieFormat
     */
    public String getMovieFormat() {
        return movieFormat;
    }

    /**
     * @param movieFormat the movieFormat to set
     */
    public void setMovieFormat(String movieFormat) {
        this.movieFormat = movieFormat;
    }

    /**
     * @return the duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * @return the poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     * @param poster the poster to set
     */
    public void setPoster(String poster) {
        this.poster = poster;
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
     * @return the category
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Categories category) {
        this.category = category;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
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
     * @return the statusMovie
     */
    public StatusMovie getStatusMovie() {
        return statusMovie;
    }

    /**
     * @param statusMovie the statusMovie to set
     */
    public void setStatusMovie(StatusMovie statusMovie) {
        this.statusMovie = statusMovie;
    }

}
