package com.miage.backendspring.service;

import com.miage.backendspring.config.DishJsonParser;
import com.miage.backendspring.entity.diet.DishNutriwi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class DietService {

    private final DishJsonParser dishJsonParser;

    public Map<String, List<DishNutriwi>> getWeeklyDiet(){

        Map<String,List<DishNutriwi>> weeklyDiet = new LinkedHashMap<>();

        List<DishNutriwi> dishes = dishJsonParser.getDietList();

        Random rand = new Random();

        for(int i = 1; i <= 7; i++){
            weeklyDiet.put("Jour_"+i, Arrays.asList(dishes.get(rand.nextInt(dishes.size())),dishes.get(rand.nextInt(dishes.size()))));
        }
        return weeklyDiet;
    }

}
