package com.service.impl;

import com.dao.RoleDao;
import com.dao.UserDao;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {



    @Autowired private UserDao userDao;


    @Autowired private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleDao.getById(3));
        userDao.save(user);

    }

    @Override
    public User findByEmail(String email) {
        User user = userDao.findByEmail(email);
        return user;
    }


    /*private final UserDao userDAO;

    private final RoleDao roleDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDAO, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> showAll()  {
        try {
            return userDAO.getAll();
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(User user)  {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            user.setRole(roleDao.getOne(3));
            return   userDAO.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User findUserById(int id) {
        return userDAO.findUserById(id);
    }

    @Override
    public boolean changeRole(User user) {
        return userDAO.changeRole(user);
    }*/
}
