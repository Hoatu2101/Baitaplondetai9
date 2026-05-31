/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tth.repository.impl;

/**
 *
 * @author Admin
 */

import com.tth.pojo.Cinemas;
import com.tth.repository.CinemaRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CinemaRepositoryImpl implements CinemaRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Cinemas> getCinemas() {

        Session s = this.factory.getCurrentSession();

        return s.createQuery(
                "FROM Cinemas ORDER BY id DESC",
                Cinemas.class
        ).getResultList();
    }

    @Override
    public Cinemas getCinemaById(int id) {

        Session s = this.factory.getCurrentSession();

        return s.get(Cinemas.class, id);
    }
}