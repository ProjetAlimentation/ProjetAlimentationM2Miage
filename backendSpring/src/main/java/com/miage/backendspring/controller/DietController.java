package com.miage.backendspring.controller;

import com.miage.backendspring.entity.ProductCart;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import com.miage.backendspring.service.DietService;
import com.miage.backendspring.service.OpenFoodFactsService;
import com.miage.backendspring.service.ProductCartService;
import com.miage.backendspring.service.profiles.ProfileEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class DietController {

    private final DietService dietService;
    private final OpenFoodFactsService openFoodFactsService;
    private final ProductCartService productCartService;

    @Operation(summary = "Get weekly diet by profile")
    @GetMapping("/getWeeklyDietByProfile")
    public ResponseEntity<String> getWeeklyDietByProfile(@RequestParam(value = "profile", required = false)
                                                                                             ProfileEnum profileEnum,
                                                         @RequestParam String username,
                                                         @RequestParam boolean refresh){
        return ResponseEntity.ok(dietService.getWeeklyDiet(username, refresh, profileEnum));
    }

    @Operation(summary = "Save a diet")
    @PostMapping("saveDiet")
    public ResponseEntity<Boolean> saveDiet(@RequestBody DishNutriwi dishNutriwi){
       return ResponseEntity.ok(dietService.addDish(dishNutriwi));
    }


    @PostMapping("/getOpenFoodFactsProducts")
    public ResponseEntity<List<OpenFoodFactsProduct>> getOpenFoodFactsProducts(@RequestBody List<String> productTypeList){
        return ResponseEntity.ok(openFoodFactsService.getOpenFoodFactsResponse(productTypeList));
    }

    @PostMapping("/addOpenFoodFactsProductsToCart")
    public void addOpenFoodFactsProductsToCart(@RequestBody ProductCart productCart){
        productCartService.addOpenFoodFactsProductsToCart(productCart);
    }


    @GetMapping("/getCartElements")
    public ResponseEntity<Set<OpenFoodFactsProduct>> getCartElements(@RequestParam String userId){
        return ResponseEntity.ok(productCartService.getCartElements(userId));
    }


    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam String userId, @RequestParam Long productId){
        productCartService.deleteProduct(userId, productId);
    }

}
