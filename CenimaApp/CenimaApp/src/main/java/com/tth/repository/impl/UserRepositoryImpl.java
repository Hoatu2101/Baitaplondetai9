/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tth.repository.impl;
import com.tth.pojo.Users;
import com.tth.repository.UserRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addUser(Users user) {
        Session s = this.factory.getObject().getCurrentSession();

        if (user.getId() == null)
            s.persist(user);
        else
            s.merge(user);
    }

    @Override
    public Users getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createQuery(
                "FROM Users \n"
                + "WHERE username=:username\n"
                + "AND active=true",
                Users.class
        );

        q.setParameter("username", username);

        List<Users> users = q.getResultList();

        if (users.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    @Override
    public List<Users> getPendingStaff() {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createQuery(
                "FROM Users WHERE role='ROLE_STAFF' AND approved=false",
                Users.class
        );

        return q.getResultList();
    }

    @Override
    public void approveStaff(int id) {

        Session s = this.factory.getObject().getCurrentSession();

        Users u = s.get(Users.class, id);

        u.setApproved(true);

        s.merge(u);
    }
}
