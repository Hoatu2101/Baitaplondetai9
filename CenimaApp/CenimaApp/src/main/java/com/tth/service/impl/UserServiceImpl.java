/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tth.pojo.Users;
import com.tth.repository.UserRepository;
import com.tth.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public void addUser(Users user) {

        Users u = this.userRepo
                .getUserByUsername(user.getUsername());

        if (u != null) {
            throw new RuntimeException(
                    "Tên tài khoản đã tồn tại!");
        }

        user.setPassword(
                this.passwordEncoder.encode(
                        user.getPassword())
        );

        user.setCreatedAt(new Date());

        user.setActive(true);

        if (user.getRole().equals("ROLE_CUSTOMER")) {
            user.setApproved(true);
        } else {
            user.setApproved(false);
        }

        if (user.getFile() != null
                && !user.getFile().isEmpty()) {

            try {

                Map res
                        = this.cloudinary.uploader().upload(
                                user.getFile().getBytes(),
                                ObjectUtils.asMap(
                                        "resource_type",
                                        "auto"
                                )
                        );

                user.setAvatar(
                        res.get("secure_url").toString());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        this.userRepo.addUser(user);
    }

    @Override
    public Users getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public List<Users> getPendingStaff() {
        return this.userRepo.getPendingStaff();
    }

    @Override
    public void approveStaff(int id) {
        this.userRepo.approveStaff(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users u = this.userRepo.getUserByUsername(username);

        if (u == null) {
            throw new UsernameNotFoundException("Invalid user");
        }

        if (!u.getApproved()) {
            throw new UsernameNotFoundException(
                    "Tài khoản chưa được duyệt");
        }

        if (!u.getActive()) {
            throw new UsernameNotFoundException(
                    "Tài khoản đã bị khóa");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRole().replace("ROLE_", ""))
                .build();
    }

}
