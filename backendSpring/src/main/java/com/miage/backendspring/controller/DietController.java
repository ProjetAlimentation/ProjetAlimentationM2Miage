package com.miage.backendspring.controller;

import com.miage.backendspring.service.profiles.ProfileEnum;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import com.miage.backendspring.service.DietService;
import com.miage.backendspring.service.OpenFoodFactsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DietController {


    private final DietService dietService;
    private final OpenFoodFactsService openFoodFactsService;

    @Operation(summary = "Get weekly diet by profile")
    @GetMapping("/getWeeklyDietByProfile")
    public ResponseEntity<Map<String, List<DishNutriwi>>> getWeeklyDietByProfile(@RequestParam(value = "profile", required = false)
                                                                                             ProfileEnum profileEnum){
        return ResponseEntity.ok(dietService.getWeeklyDiet(profileEnum));
    }

    @Operation(summary = "Save a diet")
    @PostMapping("saveDiet")
    public ResponseEntity<Boolean> saveDiet(@RequestBody DishNutriwi dishNutriwi){
       return ResponseEntity.ok(dietService.addDish(dishNutriwi));
    }


    @GetMapping("/getOpenFoodFactsProducts")
    public ResponseEntity<List<OpenFoodFactsProduct>> getOpenFoodFactsProducts(@RequestParam(value = "productType")
                                                                           String productType){
        String queryStr = null;
        try {
             queryStr = URLDecoder.decode(productType, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(openFoodFactsService.getOpenFoodFactsResponse(queryStr).getProducts());
    }
}
