/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

import com.tth.pojo.Movies;
import com.tth.repository.MoviesRepositories;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
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
public class MoviesRepositoriesImpl implements MoviesRepositories {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Movies> getMovies(Map<String, String> params) {

        if (params == null) {
            params = new HashMap<>();
        }

        Session s = factory.getCurrentSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();

        CriteriaQuery<Movies> cq = cb.createQuery(Movies.class);

        Root<Movies> root = cq.from(Movies.class);

        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {

            // SEARCH KEYWORD
            String kw = params.get("kw");

            if (kw != null && !kw.isEmpty()) {

                predicates.add((Predicate) cb.like(
                        root.get("movieName"),
                        "%" + kw + "%"
                ));
            }

            // FILTER CATEGORY
            String cateId = params.get("cateId");

            if (cateId != null && !cateId.isEmpty()) {

                predicates.add((Predicate) cb.equal(root.get("category").get("id"),
                        Integer.valueOf(cateId)
                ));
            }

            String fromPrice
                    = params.get("fromPrice");

            if (fromPrice != null
                    && !fromPrice.isBlank()) {

                predicates.add(
                        cb.ge(root.get("price"),
                                Double.valueOf(
                                        fromPrice)
                        )
                );
            }

            String toPrice
                    = params.get("toPrice");

            if (toPrice != null
                    && !toPrice.isBlank()) {

                predicates.add(
                        cb.le(root.get("price"),
                                Double.valueOf(
                                        toPrice)
                        )
                );
            }
        }

        cq.where(predicates.toArray(Predicate[]::new));

        String sort
                = params.get("sort");

        if (sort != null) {

            switch (sort) {

                case "priceAsc" ->
                    cq.orderBy(
                            cb.asc(
                                    root.get("price")));

                case "priceDesc" ->
                    cq.orderBy(
                            cb.desc(
                                    root.get("price")));

                case "durationAsc" ->
                    cq.orderBy(
                            cb.asc(
                                    root.get("duration")));

                case "durationDesc" ->
                    cq.orderBy(
                            cb.desc(
                                    root.get("duration")));

                default ->
                    cq.orderBy(
                            cb.desc(
                                    root.get("id")));
            }

        } else {

            cq.orderBy(
                    cb.desc(
                            root.get("id")));
        }

        Query<Movies> query = s.createQuery(cq);

        // PAGINATION
        if (params != null) {

            String page = params.get("page");

            int pageSize = 20;

            if (page != null && !page.isEmpty()) {

                int p = Integer.parseInt(page);

                int start = (p - 1) * pageSize;

                query.setFirstResult(start);

                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public Movies getMovieById(int id) {

        Session s = factory.getCurrentSession();

        return s.get(Movies.class, id);
    }

    @Override
    public void addOrUpdate(Movies movie) {

        Session s = factory.getCurrentSession();

        if (movie.getId() == null) {
            s.persist(movie);
        } else {
            s.merge(movie);
        }
    }

    @Override
    public void deleteMovie(int id) {

        Session s = factory.getCurrentSession();

        Movies movie = this.getMovieById(id);

        if (movie == null) {
            throw new RuntimeException("Phim không tồn tại!");
        }

        if (movie.getShowtimesList() != null
                && !movie.getShowtimesList().isEmpty()) {

            throw new RuntimeException(
                    "Phim đã có suất chiếu, không thể xóa!"
            );
        }

        s.remove(movie);
    }

    @Override
    public long countMovies() {

        Session s = factory.getCurrentSession();

        return s.createQuery(
                "SELECT COUNT(*) FROM Movies",
                Long.class
        ).getSingleResult();
    }
}
