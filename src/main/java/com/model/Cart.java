package com.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "basket")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne
            @JoinColumn(name = "user_id")
    User user;

    @OneToOne
            @JoinColumn(name = "product_id")
    Product product;

    @Column(name = "count")
    int count = 1;

    public Cart() {
    }

    public Cart(User user, Product product, int count) {
        this.user = user;
        this.product = product;
        this.count = count;
    }


}
