package com.miage.backendspring.dao;

import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import com.miage.backendspring.entity.diet.OpenFoodFactsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OpenFoodFactsAPI {


    private final String uri = "https://fr.openfoodfacts.org";


    public OpenFoodFactsResponse getOpenFoodFactsProducts(String productType) {
        final String searchUrl = uri.concat("/cgi/search.pl");


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("action", "process")
                .queryParam("tagtype_0", "categories")
                .queryParam("tag_contains_0", "contains")
                .queryParam("tag_0", productType)
                .queryParam("json", "true")
                .queryParam("fields", "product_name_fr,brands,stores,image_front_small_url")
                .queryParam("page_size", "5");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OpenFoodFactsResponse> result =
                restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, OpenFoodFactsResponse.class);
        return result.getBody();

    }



}