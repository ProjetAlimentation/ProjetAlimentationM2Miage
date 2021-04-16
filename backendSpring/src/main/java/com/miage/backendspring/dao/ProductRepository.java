package com.miage.backendspring.dao;

import com.miage.backendspring.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductCart, Integer> {
}
