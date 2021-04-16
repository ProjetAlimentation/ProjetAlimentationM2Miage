package com.miage.backendspring.service;

import com.miage.backendspring.dao.OpenFoodFactsAPI;
import com.miage.backendspring.entity.diet.OpenFoodFactsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OpenFoodFactsService {

    private final OpenFoodFactsAPI openFoodFactsAPI;


    public OpenFoodFactsResponse getOpenFoodFactsResponse(String productType){

        String strFilter = productType;

        if(productType.contains(",")){
            strFilter = productType.substring(0, productType.indexOf(","));
        }

        OpenFoodFactsResponse openFoodFactsResponse = openFoodFactsAPI.getOpenFoodFactsProducts(strFilter);

        return openFoodFactsResponse;
    }

}
