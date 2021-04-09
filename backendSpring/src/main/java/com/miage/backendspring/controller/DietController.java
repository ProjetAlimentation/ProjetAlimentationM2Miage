package com.miage.backendspring.controller;

import com.miage.backendspring.entity.ProfileEnum;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.service.DietService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DietController {


    private final DietService dietService;

    @Operation(summary = "Get weekly diet, 2 dishes a day")
    @GetMapping("/getWeeklyDiet")
    public ResponseEntity<Map<String, List<DishNutriwi>>> getWeeklyDiet(){
        return ResponseEntity.ok(dietService.getWeeklyDiet());
    }

    @Operation(summary = "Get weekly diet by profile")
    @GetMapping("/getWeeklyDietByProfile")
    public ResponseEntity<Map<String, List<DishNutriwi>>> getWeeklyDietByProfile(@RequestParam("profileEnum") ProfileEnum profileEnum){
        return ResponseEntity.ok(dietService.getWeeklyDiet(profileEnum));
    }

    @Operation(summary = "Save a diet")
    @PostMapping("saveDiet")
    public ResponseEntity<Boolean> saveDiet(@RequestBody DishNutriwi dishNutriwi){
       return ResponseEntity.ok(dietService.addDish(dishNutriwi));
    }
}
