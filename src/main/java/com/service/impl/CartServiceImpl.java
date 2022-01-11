package com.service.impl;

import com.dao.CartDao;
import com.dao.ProductDao;
import com.dao.UserDao;
import com.model.Cart;
import com.model.Product;
import com.model.User;
import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;


    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;


    @Override
    public void addProductToCart(int userId, int productId) {

        boolean exists = cartDao.existsByUser_IdAndProduct_Id(userId, productId);

        if (exists) {
            cartDao.incrementCount(userId, productId);
        } else {
            User user =  userDao.getById(userId);
            Product product = productDao.getById(productId);
            cartDao.save(new Cart(user, product, 1));
        }
    }

    @Override
    public void deleteProductFromCart(int userId, int productId) {

        boolean moreThanOne = cartDao.checkCount(userId, productId) > 1;

        if (moreThanOne) {
            cartDao.decrementCount(userId, productId);
        } else {
            cartDao.deleteByUser_IdAndProduct_Id(userId, productId);
        }
    }

    @Override
    public List<Cart> showCartByUser(int userId) {
        return cartDao.findAllByUserIdOrderByProductNameDesc(userId);
    }
}
