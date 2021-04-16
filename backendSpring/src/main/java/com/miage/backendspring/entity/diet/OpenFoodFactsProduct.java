package com.miage.backendspring.entity.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.miage.backendspring.entity.ProductCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "OPEN_FOOD_FACTS_PRODUCTS")
public class OpenFoodFactsProduct {

    @Id
    @JsonProperty("_id")
    @GeneratedValue
    private long id;

    @JsonProperty("product_name_fr")
    private String productName;
    private String brands;
    @JsonProperty("image_front_small_url")
    private String image;
    private String stores;
    @JsonProperty("nutrition_grades")
    private String nutritionGrade;

    @ManyToOne
    @JoinColumn(name="productcard_id", nullable=false)
    private ProductCart cart;
}
