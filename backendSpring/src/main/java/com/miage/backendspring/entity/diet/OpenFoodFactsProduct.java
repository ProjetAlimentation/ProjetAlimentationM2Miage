package com.miage.backendspring.entity.diet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OpenFoodFactsProduct {

    @JsonProperty("product_name_fr")
    private String productName;
    private String brands;
    @JsonProperty("image_front_small_url")
    private String image;
    private String stores;


}
