package com.miage.backendspring.controller;

import com.miage.backendspring.entity.Profil;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.service.DietService;
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

    @GetMapping("/getWeeklyDiet")
    public ResponseEntity<Map<String, List<DishNutriwi>>> getWeeklyDiet(){
        return ResponseEntity.ok(dietService.getWeeklyDiet());
    }

    @GetMapping("/getWeeklyDietNutriwi")
    public ResponseEntity<Map<String, List<DishNutriwi>>> getWeeklyDiet(@RequestParam("profil") Profil profil){
        return ResponseEntity.ok(dietService.getWeeklyDiet(profil));
    }

    @PostMapping("saveDiet")
    public ResponseEntity<Boolean> saveDiet(@RequestBody DishNutriwi dishNutriwi){
       return ResponseEntity.ok(dietService.addDish(dishNutriwi));
    }
}
