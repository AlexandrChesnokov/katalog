package com.dao;

import com.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface CartDao extends JpaRepository<Cart, Integer> {

    void deleteByUser_IdAndProduct_Id(int user_id, int product_id);

    List<Cart> findAllByUserIdOrderByProductNameDesc(int user_id);

    boolean existsByUser_IdAndProduct_Id(int user_id, int product_id);


    @Query(value = "select b.count from basket b where user_id = ?1 and product_id = ?2", nativeQuery = true)
    int checkCount(int user_id, int product_id);

    @Modifying
    @Query( value = "update basket set count = (select count from basket where user_id = ?1 and product_id = ?2)+1" +
            " where user_id = ?1 and product_id = ?2", nativeQuery = true)
    void incrementCount(int user_id, int product_id);

    @Modifying
    @Query( value = "update basket set count = (select count from basket where user_id = ?1 and product_id = ?2)-1" +
            " where user_id = ?1 and product_id = ?2", nativeQuery = true)
    void decrementCount(int user_id, int product_id);

}
