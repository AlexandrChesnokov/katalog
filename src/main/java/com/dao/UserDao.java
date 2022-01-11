package com.dao;

import com.model.Role;
import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
