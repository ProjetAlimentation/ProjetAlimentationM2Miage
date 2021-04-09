package com.miage.backendspring.service;

import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.entity.ProfileEnum;
import com.miage.backendspring.entity.diet.DishNutriwi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DietService {

    private final DietDAO dietDAO;

    /**
     * Generate weekly diet containing 2 dishes a day randomly
     * @return
     */
    public Map<String, List<DishNutriwi>> getWeeklyDiet(){

        Map<String,List<DishNutriwi>> weeklyDiet = new LinkedHashMap<>();

        List<DishNutriwi> dishes = dietDAO.getDietList();

        Random rand = new Random();

        for(int i = 1; i <= 7; i++){
            weeklyDiet.put("Jour_"+i, Arrays.asList(dishes.get(rand.nextInt(dishes.size())),dishes.get(rand.nextInt(dishes.size()))));
        }
        return weeklyDiet;
    }


    /**
     * Generate weekly diet containing 2 dishes a day randomly by profile
     * @param profileEnum
     * @return
     */
    public Map<String, List<DishNutriwi>> getWeeklyDiet(ProfileEnum profileEnum){

        Map<String,List<DishNutriwi>> weeklyDiet = new LinkedHashMap<>();

        List<DishNutriwi> dishes = dietDAO.getDietList().stream().filter(e -> e.getProfile().contains(profileEnum.toString())).collect(Collectors.toList());

        Random rand = new Random();

        for(int i = 1; i <= 7; i++){
            weeklyDiet.put("Jour_"+i, Arrays.asList(dishes.get(rand.nextInt(dishes.size())),dishes.get(rand.nextInt(dishes.size()))));
        }
        return weeklyDiet;
    }


    /**
     * Add a dish to list
     * @param dishNutriwi
     * @return
     */
    public Boolean addDish(DishNutriwi dishNutriwi){
        return dietDAO.addDishToDietList(dishNutriwi);
    }


}
