package com.dao;



import com.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
