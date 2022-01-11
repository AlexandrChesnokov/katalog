package com.service;

import com.model.Cart;

import java.util.List;

public interface CartService {
    void addProductToCart(int userId, int productId);

    List<Cart> showCartByUser(int userId);

    void deleteProductFromCart(int userId, int productId);
}
