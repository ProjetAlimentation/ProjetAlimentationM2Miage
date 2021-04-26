package com.miage.backendspring.service;

import com.miage.backendspring.dao.OpenFoodFactsAPI;
import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import com.miage.backendspring.entity.diet.OpenFoodFactsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class OpenFoodFactsService {

    private final OpenFoodFactsAPI openFoodFactsAPI;


    public List<OpenFoodFactsProduct> getOpenFoodFactsResponse(List<String> productType) {

        Set<String> productTypeFiltered = new HashSet<>();

        List<String> strings = Arrays.asList(",", " ou ");

        for (String product : productType) {
            String substring = product;
            if (product.contains(strings.get(0))) {
                substring = product.substring(0, product.indexOf(strings.get(0)));
                if (product.contains(strings.get(1))) {
                    substring = product.substring(0, product.indexOf(strings.get(1)));
                }
            }
            productTypeFiltered.add(substring);
        }


        List<OpenFoodFactsProduct> openFoodFactsProducts = new ArrayList<>();

        for (String str : productTypeFiltered) {
            OpenFoodFactsResponse response = openFoodFactsAPI.getOpenFoodFactsProducts(str);
            if (!response.getProducts().isEmpty()) {
                OpenFoodFactsProduct e = response.getProducts().get(0);

                /*long count = 0;

                if(e.getStores() == null){
                    count = e.getStores().chars().filter(ch -> ch == ',').count();
                }

                StringBuilder stringBuilder = new StringBuilder();

                if (count > 3) {
                    stringBuilder.append(e.getStores(), 0, str.indexOf(","));
                    stringBuilder.append(e.getStores(), 1, str.indexOf(","));
                    stringBuilder.append(e.getStores(), 2, str.indexOf(","));
                } else {
                    stringBuilder.append(e.getStores());
                }
                e.setStores(stringBuilder.toString());*/
                openFoodFactsProducts.add(e);

            }
        }

        return openFoodFactsProducts;
    }

}
