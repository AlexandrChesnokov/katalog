package com.dao;

import com.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

public interface RoleDao extends JpaRepository<Role, Integer> {

    /*public Role getOne(int id) throws SQLException;*/
}
