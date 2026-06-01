/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repository;

import com.tth.pojo.Users;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserRepository {

    void addUser(Users user);

    Users getUserByUsername(String username);

    List<Users> getPendingStaff();

    void approveStaff(int id);

    List<Users> getAllUsers();

    Users getUserById(Integer id);

    void lockUser(Integer id);

    void unlockUser(Integer id);
}
