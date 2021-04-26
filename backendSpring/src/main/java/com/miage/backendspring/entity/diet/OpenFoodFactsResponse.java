package com.miage.backendspring.entity.diet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OpenFoodFactsResponse {

    private List<OpenFoodFactsProduct> products;


}
