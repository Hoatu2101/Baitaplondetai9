/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

import com.tth.pojo.Categories;
import com.tth.service.CategoryServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tth.repository.CategoryRepositories;


/**
 *
 * @author Administrator
 */
@Service

public class CategoryServicesImpl implements CategoryServices {
@Autowired
private CategoryRepositories cateRepo;
@Override
public List<Categories> getCates() {
    return this.cateRepo.getCates();
}



}
