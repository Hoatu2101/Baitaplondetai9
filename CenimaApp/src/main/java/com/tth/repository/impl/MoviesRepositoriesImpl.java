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
import java.awt.event.AWTEventListener;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Repository
@PropertySource("classpath:configs.properties")
@Transactional
public class MoviesRepositoriesImpl implements MoviesRepositories {

    @Autowired
    private Environment env;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Movies> getMovies(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Movies> m = b.createQuery(Movies.class);
        Root root = m.from(Movies.class);
        m.select(root);
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("title"), String.format("%%%s%%", kw)));

            }
            String cateId = params.get("cateId");
            if (cateId != null && !cateId.isEmpty()) {
                predicates.add(b.equal(root.get("idCategory").as(Integer.class), cateId));

            }
            m.where(predicates.toArray(Predicate[]::new));
        }
        m.orderBy(b.desc(root.get("id")));

        Query query = session.createQuery(m);
        if (params != null) {
            int pageSize = this.env.getProperty("movies.page_size", Integer.class,8);
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int start = (page - 1) * pageSize;

            query.setMaxResults(pageSize);
            query.setFirstResult(start);
        }

        return query.getResultList();

    }

    @Override
    public void addOrUpdateMovies(Movies p) {
        // validate cơ bản
    if (p == null) {
        throw new IllegalArgumentException("Phim  không được null");
    }

    if (p.getTitle()== null || p.getTitle().trim().isEmpty()) {
        throw new IllegalArgumentException("Tên phim không được rỗng");
    }
    Session s = this.factory.getObject().getCurrentSession();

    if (p.getId() == null) {
        s.persist(p);
    } else {
        s.merge(p);
    }
    }

    @Override
    public Movies getMovieById(int id) {
          Session s = this.factory.getObject().getCurrentSession();
        return s.get(Movies.class, id);
    }

    @Override
    public void deleteMovie(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
