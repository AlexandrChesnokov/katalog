package com.controller;


import com.model.User;
import com.service.CartService;
import com.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Slf4j
@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
    @GetMapping("products")
    public String getProducts(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = user.getId();
        log.info("MVC: Received a request from {} to get a list of all products", user.getEmail());
        model.addAttribute("products", productService.showAllProducts());
        log.info("MVC: A response has been sent to user {} with a list of products", user.getEmail());
        model.addAttribute("userid", userId);
        return "products";
    }
}
