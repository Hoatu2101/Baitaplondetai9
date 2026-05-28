/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository.impl;

import com.tth.pojo.Categories;
import com.tth.repository.CategoryRepositories;

import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
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
public class CategoryRepositoriesImpl implements CategoryRepositories {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
   public List<Categories> getCates() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Categories", Categories.class);
        return query.getResultList();
    }
}
