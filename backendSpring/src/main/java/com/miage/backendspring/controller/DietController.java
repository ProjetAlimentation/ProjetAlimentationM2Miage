package com.miage.backendspring.controller;

import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.service.DietService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DietController {


    private final DietService dietService;

    @GetMapping("/getWeeklyDiet")
    public ResponseEntity<Map<String, List<DishNutriwi>>> getWeeklyDiet(){
        return ResponseEntity.ok(dietService.getWeeklyDiet());
    }
}
