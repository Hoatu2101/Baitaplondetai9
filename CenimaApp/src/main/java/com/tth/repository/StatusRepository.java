/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tth.repository;

/**
 *
 * @author Administrator
 */
import com.tth.pojo.Status;
import java.util.List;

public interface StatusRepository {

    List<Status> getStatuses();

    Status getStatusById(int id);

    void addOrUpdate(Status status);

    void deleteStatus(Integer id);
}
