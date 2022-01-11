package com.controller.rest;

import com.model.Cart;
import com.model.Product;
import com.model.User;
import com.service.CartService;
import com.service.ProductService;
import com.service.UserService;
import com.util.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "api/")
public class UserRestController {

    private final UserService userService;

    private final ProductService productService;

    private final UserInfo userInfo;

    private final CartService cartService;

    public UserRestController(UserService userService, ProductService productService, UserInfo userInfo, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.userInfo = userInfo;
        this.cartService = cartService;
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getProducts(HttpServletRequest req) {
        User user = userService.findByEmail(userInfo.getJwtUserEmail(req));
        log.info("REST: Received a request from {} to get a list of all products", user.getEmail());
        List<Product> products = productService.showAllProducts();
        log.info("REST: A response has been sent to user {} with a list of products", user.getEmail());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("products/my-cart")
    public ResponseEntity<List<Cart>> getCart(HttpServletRequest req) {
        User user = userService.findByEmail(userInfo.getJwtUserEmail(req));
        log.info("REST: Received a request from {} to get a cart", user.getEmail());
        List<Cart> carts = cartService.showCartByUser(user.getId());
        log.info("REST: A response has been sent to user {} with a cart", user.getEmail());
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @DeleteMapping("products/my-cart/{product_id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "product_id") int product_id,
                                                HttpServletRequest req) {
        User user = userService.findByEmail(userInfo.getJwtUserEmail(req));
        log.info("REST: Received a request from {} to remove a product with ID {} from the cart", user.getEmail(), product_id);
        cartService.deleteProductFromCart(user.getId(), product_id);
        log.info("REST: Product with id {} was successfully removed from {}  cart", product_id, user.getEmail());
        return new ResponseEntity<>("delete successful", HttpStatus.OK);
    }

    @PutMapping("products/my-cart/{product_id}")
    public ResponseEntity<Object> addProduct(@PathVariable(value = "product_id") int product_id,
                                                    HttpServletRequest req) {
        User user = userService.findByEmail(userInfo.getJwtUserEmail(req));
        log.info("REST: Received a request from {} to add a product with ID {} from the cart", user.getEmail(), product_id);
        cartService.addProductToCart(user.getId(), product_id);
        log.info("REST: Product with id {} was successfully added to {} cart", product_id, user.getEmail());
        return new ResponseEntity<>("add successful", HttpStatus.OK);
    }
}
