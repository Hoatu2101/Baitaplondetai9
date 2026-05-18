/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service;

/**
 *
 * @author Admin
 */

import com.tth.pojo.Users;
import java.util.List;

public interface UserService {

    void addUser(Users user);

    Users getUserByUsername(String username);

    List<Users> getPendingStaff();

    void approveStaff(int id);
}
