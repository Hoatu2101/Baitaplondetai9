/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;


/**
 *
 * @author Admin
 */

import com.tth.pojo.Showtimes;
import com.tth.pojo.Status;
import com.tth.repository.ShowtimeRepository;
import com.tth.repository.StatusRepository;
import com.tth.service.ShowtimeService;
import com.tth.service.StatusService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    
    @Autowired
    private StatusRepository statusRepo;
    
        
    @Override
    public List<Status> getStatuses() {
        return this.statusRepo.getStatuses();
    }

    @Override
    public Status getStatusById(int id) {
        return this.statusRepo.getStatusById(id);
    }


}