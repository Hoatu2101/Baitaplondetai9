/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

/**
 *
 * @author Administrator
 */

import com.tth.pojo.Status;
import com.tth.repository.StatusRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StatusRepositoryImpl implements StatusRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Status> getStatuses() {

        Session s = this.factory.getCurrentSession();

        return s.createQuery(
                "FROM Statuses ORDER BY id ASC",
                Status.class
        ).getResultList();
    }

    @Override
    public Status getStatusById(int id) {

        Session s = this.factory.getCurrentSession();

        return s.get(Status.class, id);
    }
}