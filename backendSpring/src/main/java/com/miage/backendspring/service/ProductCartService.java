package com.miage.backendspring.service;

import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.dao.ProductRepository;
import com.miage.backendspring.entity.ProductCart;
import com.miage.backendspring.entity.ProfileEnum;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ProductCartService {

    private final ProductRepository productRepository;


    public void addOpenFoodFactsProductsToCart(ProductCart productCart){

        ProductCart productCart1 = new ProductCart();
        productRepository.save(productCart);

    }


}
