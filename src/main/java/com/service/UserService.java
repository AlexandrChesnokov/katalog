package com.service;

import com.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void save(User user);

    User findByEmail(String email);



    /*List<User> showAll() throws SQLException;

    boolean save(User user) throws SQLException;

    User findByEmail(String email);

    User findUserById(int id);

    boolean changeRole(User user);*/

}
