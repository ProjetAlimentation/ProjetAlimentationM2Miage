package com.miage.backendspring.entity.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.miage.backendspring.entity.ProductCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "OPEN_FOOD_FACTS_PRODUCTS")
public class OpenFoodFactsProduct {

    @Id
    @JsonProperty("_id")
    private long _id;

    @JsonProperty("product_name_fr")
    private String productName;
    private String brands;
    @JsonProperty("image_front_small_url")
    private String image;
    private String stores;
    @JsonProperty("nutrition_grades")
    private String nutritionGrade;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "openFoodFactsProducts")
    @JsonIgnore
    private Set<ProductCart> productCarts = new HashSet<>();


}
