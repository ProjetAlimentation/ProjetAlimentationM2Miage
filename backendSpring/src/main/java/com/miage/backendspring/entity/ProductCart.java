package com.miage.backendspring.entity;

import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PRODUCT_CART")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "productcart_has_product",
            joinColumns = @JoinColumn(name = "productcart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<OpenFoodFactsProduct> openFoodFactsProducts = new HashSet<>();


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "username")
    private User user;

}
