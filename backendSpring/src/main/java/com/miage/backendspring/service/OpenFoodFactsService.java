package com.miage.backendspring.service;

import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.dao.OpenFoodFactsAPI;
import com.miage.backendspring.entity.ProfileEnum;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.entity.diet.OpenFoodFactsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

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
