/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */


import com.tth.pojo.Status;
import java.util.List;

public interface StatusService {
    List<Status> getStatuses();
    Status getStatusById(int id);
}