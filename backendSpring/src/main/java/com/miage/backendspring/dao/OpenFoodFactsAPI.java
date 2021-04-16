package com.miage.backendspring.dao;

import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import com.miage.backendspring.entity.diet.OpenFoodFactsResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OpenFoodFactsAPI {


    private final String uri = "https://fr.openfoodfacts.org";


    public OpenFoodFactsResponse getOpenFoodFactsProducts(String productType) {
        final String searchUrl = uri.concat("/cgi/search.pl");

        RestTemplate restTemplate = new RestTemplate();


        String encode = null;
        try {
            encode = URLEncoder.encode(StringUtils.stripAccents(productType), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String enocodedUrl = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("action", "process")
                .queryParam("tagtype_0", "categories")
                .queryParam("tag_contains_0", "contains")
                .queryParam("tag_0", encode)
                .queryParam("json", "true")
                .queryParam("fields", "_id,product_name_fr,brands,stores,image_front_small_url,nutrition_grades")
                .queryParam("page_size", "2")
                .queryParam("sort_by", "unique_scans_n")
                .encode()
                .build(true).toUriString();

        ResponseEntity<OpenFoodFactsResponse> forEntity = restTemplate.getForEntity(enocodedUrl, OpenFoodFactsResponse.class);

        return forEntity.getBody();

    }



}