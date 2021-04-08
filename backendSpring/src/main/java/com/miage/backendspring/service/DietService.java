package com.miage.backendspring.service;

import com.miage.backendspring.config.DishJsonParser;
import com.miage.backendspring.entity.diet.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DietService {

    private final DishJsonParser dishJsonParser;

    public Map<String, List<Dish>> getWeeklyDiet(){

        Map<String,List<Dish>> weeklyDiet = new LinkedHashMap<>();

        List<Dish> dishes = dishJsonParser.parseJson().stream()
                .filter(o -> o.getType().equals("Plat principal"))
                .collect(Collectors.toList());

        Random rand = new Random();

        for(int i = 1; i <= 7; i++){
            weeklyDiet.put("Jour_"+i, Arrays.asList(dishes.get(rand.nextInt(dishes.size())),dishes.get(rand.nextInt(dishes.size()))));
        }
        return weeklyDiet;
    }

}
