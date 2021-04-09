package com.miage.backendspring.service;

import com.miage.backendspring.config.DishJsonParser;
import com.miage.backendspring.entity.Profil;
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

    public Map<String, List<DishNutriwi>> getWeeklyDiet(Profil profil){

        Map<String,List<DishNutriwi>> weeklyDiet = new LinkedHashMap<>();

        List<DishNutriwi> dishes = new ArrayList<DishNutriwi>();
        dishes = (List<DishNutriwi>) dishJsonParser.getDietList().stream().filter(e -> e.getProfile().contains(profil.toString()));

        /*while (it.hasNext())
        {
            DishNutriwi dn = it.next();
            if(dn.getProfile().contains(profil.toString()))
            {
                dishes.add(dn);
            }
        }*/
        Random rand = new Random();

        for(int i = 1; i <= 7; i++){
            weeklyDiet.put("Jour_"+i, Arrays.asList(dishes.get(rand.nextInt(dishes.size())),dishes.get(rand.nextInt(dishes.size()))));
        }
        return weeklyDiet;
    }
}
