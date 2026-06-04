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
import java.util.Map;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    Users addUser(Map<String, String> info, MultipartFile avatar);
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
