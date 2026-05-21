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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
//@NamedQueries({
//    @NamedQuery(name = "Movies.findAll", query = "SELECT m FROM Movies m"),
//    @NamedQuery(name = "Movies.findByTitle", query = "SELECT m FROM Movies m WHERE m.title = :title"),
//    @NamedQuery(name = "Movies.findByReleaseDate", query = "SELECT m FROM Movies m WHERE m.releaseDate = :releaseDate"),
//    @NamedQuery(name = "Movies.findByGenre", query = "SELECT m FROM Movies m WHERE m.genre = :genre"),
//    @NamedQuery(name = "Movies.findByDirector", query = "SELECT m FROM Movies m WHERE m.director = :director"),
//    @NamedQuery(name = "Movies.findByPosterUrl", query = "SELECT m FROM Movies m WHERE m.posterUrl = :posterUrl"),
//    @NamedQuery(name = "Movies.findById", query = "SELECT m FROM Movies m WHERE m.id = :id"),
//    @NamedQuery(name = "Movies.findByCreatedAt", query = "SELECT m FROM Movies m WHERE m.createdAt = :createdAt")})
//public class Movies implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 255)
//    @Column(name = "title")
//    private String title;
//    @Lob
//    @Size(max = 65535)
//    @Column(name = "description")
//    private String description;
//    @Column(name = "release_date")
//    @Temporal(TemporalType.DATE)
//    private Date releaseDate;
//    @Size(max = 100)
//    @Column(name = "genre")
//    private String genre;
//    @Size(max = 255)
//    @Column(name = "director")
//    private String director;
//    @Lob
//    @Size(max = 65535)
//    @Column(name = "cast")
//    private String cast;
//    @Size(max = 255)
//    @Column(name = "poster_url")
//    private String posterUrl;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//    @JoinColumn(name = "id_category", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Categories idCategory;
//    @JoinColumn(name = "status_id", referencedColumnName = "id")
//    @ManyToOne
//    private StatusMovie statusId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
//    private List<Showtimes> showtimesList;
//    @Transient
//    private MultipartFile file;
//    public Movies() {
//    }
//
//    public Movies(Integer id) {
//        this.id = id;
//    }
//
//    public Movies(Integer id, String title) {
//        this.id = id;
//        this.title = title;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Date getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(Date releaseDate) {
//        this.releaseDate = releaseDate;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public String getDirector() {
//        return director;
//    }
//
//    public void setDirector(String director) {
//        this.director = director;
//    }
//
//    public String getCast() {
//        return cast;
//    }
//
//    public void setCast(String cast) {
//        this.cast = cast;
//    }
//
//    public String getPosterUrl() {
//        return posterUrl;
//    }
//
//    public void setPosterUrl(String posterUrl) {
//        this.posterUrl = posterUrl;
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
//    public Categories getIdCategory() {
//        return idCategory;
//    }
//
//    public void setIdCategory(Categories idCategory) {
//        this.idCategory = idCategory;
//    }
//
//    public StatusMovie getStatusId() {
//        return statusId;
//    }
//
//    public void setStatusId(StatusMovie statusId) {
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
//        if (!(object instanceof Movies)) {
//            return false;
//        }
//        Movies other = (Movies) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.tth.pojo.Movies[ id=" + id + " ]";
//    }
//    public MultipartFile getFile() {
//        return file;
//    }
//
//    /**
//     * @param file the file to set
//     */
//    public void setFile(MultipartFile file) {
//        this.file = file;
//    }
//    
//}
@Entity
@Table(name = "movies")
public class Movies implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String poster;

    private String trailer;

    private Double price;

    @Column(name = "movie_format")
    private String movieFormat;

    private Integer duration;

    private Boolean active = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusMovie statusMovie;

    public Movies() {
    }

    public Movies(Integer id, String movieName, String description, String poster, String trailer, Double price, String movieFormat, Integer duration, Categories category) {
        this.id = id;
        this.movieName = movieName;
        this.description = description;
        this.poster = poster;
        this.trailer = trailer;
        this.price = price;
        this.movieFormat = movieFormat;
        this.duration = duration;
        this.category = category;
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
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
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
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
