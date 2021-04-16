package com.miage.backendspring.entity;

import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "PRODUCT_CART")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy="cart",  cascade = CascadeType.ALL)
    private Set<OpenFoodFactsProduct> openFoodFactsProducts = new HashSet<>();


    @OneToOne(mappedBy = "productCart")
    private User user;


    public void addProductToSet(Set<OpenFoodFactsProduct> list){
        openFoodFactsProducts.addAll(list);
    }
}
