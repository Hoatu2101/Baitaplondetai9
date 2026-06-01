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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void addUser(Users user);

    Users getUserByUsername(String username);

    List<Users> getPendingStaff();

    void approveStaff(int id);

    UserDetails loadUserByUsername(String username);

    List<Users> getAllUsers();

    Users getUserById(Integer id);

    void lockUser(Integer id);

    void unlockUser(Integer id);
}
