/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

import com.tth.pojo.Movies;
import com.tth.repository.MoviesRepositories;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Repository
@Transactional
public class MoviesRepositoriesImpl
        implements MoviesRepositories {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Movies> getMovies(Map<String, String> params){
          String kw = params.get("kw");

    Integer cateId = null;

    if (params.get("cateId") != null) {
        cateId = Integer.parseInt(params.get("cateId"));
    }

    int page = 1;

    if (params.get("page") != null) {
        page = Integer.parseInt(params.get("page"));
    }

      return this.getMovies(kw, cateId, page);
    }
    @Override
    public List<Movies> getMovies(
            String kw,
            Integer cateId,
            int page) {

        Session s = factory.getObject().getCurrentSession();

        String hql = "FROM Movies m WHERE m.active = true";

        if (kw != null && !kw.trim().isEmpty()) {
            hql += " AND m.movieName LIKE :kw";
        }

        if (cateId != null) {
            hql += " AND m.category.id = :cateId";
        }

        hql += " ORDER BY m.id DESC";

        Query<Movies> q =
                s.createQuery(hql, Movies.class);

        if (kw != null && !kw.trim().isEmpty()) {
            q.setParameter("kw", "%" + kw + "%");
        }

        if (cateId != null) {
            q.setParameter("cateId", cateId);
        }

        int PAGE_SIZE = 20;

        q.setFirstResult((page - 1) * PAGE_SIZE);
        q.setMaxResults(PAGE_SIZE);

        return q.getResultList();
    }
    
    @Override
    public Movies getMovieById(int id) {

        Session s = factory.getObject().getCurrentSession();

        return s.get(Movies.class, id);
    }

    @Override
    public void addOrUpdate(Movies movie) {

        Session s = factory.getObject().getCurrentSession();

        if (movie.getId() == null) {

            movie.setActive(true);

            s.persist(movie);

        } else {

            Movies oldMovie =
                    s.get(Movies.class, movie.getId());

            if (movie.getPoster() == null
                    || movie.getPoster().isEmpty()) {

                movie.setPoster(oldMovie.getPoster());
            }

            movie.setCreatedDate(
                    oldMovie.getCreatedDate());

            movie.setActive(true);

            s.merge(movie);
        }
    }

    @Override
    public void deleteMovie(int id) {

        Session s = factory.getObject().getCurrentSession();
        

        Movies movie = this.getMovieById(id);

        if (movie != null) {

            movie.setActive(false);

            s.merge(movie);
        }
    }
}