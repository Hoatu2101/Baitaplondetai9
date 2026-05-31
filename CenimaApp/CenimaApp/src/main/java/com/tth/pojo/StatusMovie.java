/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "status_movie")
@NamedQueries({
    @NamedQuery(name = "StatusMovie.findAll", query = "SELECT s FROM StatusMovie s"),
    @NamedQuery(name = "StatusMovie.findById", query = "SELECT s FROM StatusMovie s WHERE s.id = :id"),
    @NamedQuery(name = "StatusMovie.findByNameStatus", query = "SELECT s FROM StatusMovie s WHERE s.nameStatus = :nameStatus")})
public class StatusMovie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_status")
    private String nameStatus;
    @OneToMany(mappedBy = "statusMovie")
    @JsonIgnore
    private List<Movies> moviesList;

    public StatusMovie() {
    }

    public StatusMovie(Integer id) {
        this.id = id;
    }

    public StatusMovie(Integer id, String nameStatus) {
        this.id = id;
        this.nameStatus = nameStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public List<Movies> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusMovie)) {
            return false;
        }
        StatusMovie other = (StatusMovie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.StatusMovie[ id=" + id + " ]";
    }
    
}
