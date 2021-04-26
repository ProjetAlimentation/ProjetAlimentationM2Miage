package com.miage.backendspring.dao;

import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenFoodFactsProductRepository extends JpaRepository<OpenFoodFactsProduct, Long> {
}
