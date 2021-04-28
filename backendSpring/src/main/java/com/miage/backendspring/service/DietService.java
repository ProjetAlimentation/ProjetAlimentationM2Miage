package com.miage.backendspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.dao.UserRepository;
import com.miage.backendspring.entity.ProductCart;
import com.miage.backendspring.entity.User;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.service.profiles.ProfileEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DietService {

    private final DietDAO dietDAO;
    private final UserRepository userRepository;
    //private final OpenFoodFactsAPI openFoodFactsAPI;

    /**
     * Generate weekly diet containing 2 dishes a day randomly by profile
     * @param profileEnum
     * @return
     */
    public String getWeeklyDiet(ProfileEnum profileEnum){

        Map<String,List<DishNutriwi>> weeklyDiet = new LinkedHashMap<>();

        List<DishNutriwi> dishes;

        if(profileEnum != null){
            dishes = dietDAO.getDietList().stream().filter(e -> e.getProfile().contains(profileEnum.toString())).collect(Collectors.toList());
        } else {
            dishes = dietDAO.getDietList();
        }

        Random rand = new Random();

        for(int i = 1; i <= 7; i++){
            DishNutriwi day = dishes.get(rand.nextInt(dishes.size()));
            DishNutriwi evening = dishes.get(rand.nextInt(dishes.size()));

            if (day.equals(evening)) {
                day = dishes.get(rand.nextInt(dishes.size()));
            }
            weeklyDiet.put("Jour_"+i, Arrays.asList(day, evening));
        }

/*        for (Map.Entry<String, List<DishNutriwi>> entry : weeklyDiet.entrySet()) {
            for (DishNutriwi d: entry.getValue()) {
                for (String str : d.getIngredients()) {
                    String strFilter = str;

                    if(str.contains(",")){
                        strFilter = ²);
                    }

                    List<OpenFoodFactsProduct> products = openFoodFactsAPI.getOpenFoodFactsProducts(strFilter).getProducts();
                    if(!products.isEmpty()){
                        OpenFoodFactsProduct openFoodFactsProduct = products.get(0);
                        d.getOpenFoodFactsProducts().add(openFoodFactsProduct);
                    }
                }
            }
        }*/


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String str = null;
        try {
            str = ow.writeValueAsString(weeklyDiet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;
    }


    public String getWeeklyDiet(String username, boolean regenerate, ProfileEnum profileEnum){

        User admin = userRepository.getOne(username);
        ProductCart productCart = admin.getProductCart();

        if(regenerate){
            String weeklyDiet = getWeeklyDiet(profileEnum);
            productCart.setWeeklyDiet(weeklyDiet);
            userRepository.save(admin);
            return weeklyDiet;
        }

        if(productCart.getWeeklyDiet() != null){
            return productCart.getWeeklyDiet();
        }

        String weeklyDiet = getWeeklyDiet(profileEnum);
        productCart.setWeeklyDiet(weeklyDiet);
        userRepository.save(admin);

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
