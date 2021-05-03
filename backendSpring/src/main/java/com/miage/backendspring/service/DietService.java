package com.miage.backendspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.entity.ProductCart;
import com.miage.backendspring.entity.User;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.repositories.UserRepository;
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
     * get the json dish list by the nutritional profile
     *
     * @param profileEnum
     * @return
     */
    public List<DishNutriwi> getDishListByProfile(ProfileEnum profileEnum) {
        List<DishNutriwi> dishes;

        if (profileEnum != null) {
            dishes = dietDAO.getDietDishList().stream().filter(e -> e.getProfile().contains(profileEnum.toString())).collect(Collectors.toList());
        } else {
            dishes = dietDAO.getDietDishList();
        }
        return dishes;
    }

    /**
     * Generate a map of the weekly diet containing 2 dishes a day randomly by nutritional profile
     * The map has a key which is a string value that corresponds to the "name" of the day (Jour_1, Jour_2, etc.)
     * For each key, the map contains a list of dishes (2 dishes a day)
     *
     * @param profileEnum
     * @return
     */
    private Map<String, List<DishNutriwi>> generateWeeklyDietMap(ProfileEnum profileEnum) {
        Map<String, List<DishNutriwi>> weeklyDiet = new LinkedHashMap<>();

        List<DishNutriwi> dishes = getDishListByProfile(profileEnum);

        Random rand = new Random();

        for (int i = 1; i <= 7; i++) {
            DishNutriwi day = dishes.get(rand.nextInt(dishes.size()));
            DishNutriwi evening = dishes.get(rand.nextInt(dishes.size()));

            if (day.equals(evening)) {
                day = dishes.get(rand.nextInt(dishes.size()));
            }
            weeklyDiet.put("Jour_" + i, Arrays.asList(day, evening));
        }
        return weeklyDiet;
    }

    /**
     * Getting the string value of a generated weeklyDietMap
     *
     * @param weeklyDiet
     * @return
     */
    public String weeklyDietMapToString(Map<String, List<DishNutriwi>> weeklyDiet) {


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String str = null;
        try {
            str = ow.writeValueAsString(weeklyDiet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * Getting the mapped object of the weeklyDietString
     *
     * @param weeklyDietString
     * @return
     */
    public Map<String, List<DishNutriwi>> weeklyDietStringToMap(String weeklyDietString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<DishNutriwi>> userWeeklyDietMap = null;
        try {
            TypeReference<HashMap<String, List<DishNutriwi>>> typeRef = new TypeReference<HashMap<String, List<DishNutriwi>>>() {
            };
            userWeeklyDietMap = mapper.readValue(weeklyDietString, typeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userWeeklyDietMap;
    }

    /**
     * If the weekly diet not regenerated by user, this method is saving the weeklyDietString (in database) of the weekly diet mapped object
     *
     * @param username
     * @param regenerate
     * @param profileEnum
     * @return
     */
    public String getWeeklyDiet(String username, boolean regenerate, ProfileEnum profileEnum) {
        Map<String, List<DishNutriwi>> weeklyDietMap = generateWeeklyDietMap(profileEnum);


        if (regenerate) {
            String weeklyDiet = weeklyDietMapToString(weeklyDietMap);
            savDietToDB(username, weeklyDiet);
            return weeklyDiet;
        }

        User admin = userRepository.getOne(username);
        ProductCart productCart = admin.getProductCart();

        if (productCart.getWeeklyDiet() != null) {
            return productCart.getWeeklyDiet();
        }
        String weeklyDiet = weeklyDietMapToString(weeklyDietMap);
        savDietToDB(username, weeklyDiet);

        return weeklyDiet;
    }


    /**
     * Regenerate a dish in the weekly diet by nutritional profile using username account
     *
     * @param username
     * @param profileEnum
     * @param dishKey
     * @param dishIndex
     * @return
     */
    public String getRegenerateDishByProfile(String username, String dishKey, int dishIndex, ProfileEnum profileEnum) {
        String userWeeklyDietStr = getWeeklyDiet(username, false, profileEnum);
        Map<String, List<DishNutriwi>> userWeeklyDietMap = weeklyDietStringToMap(userWeeklyDietStr);

        String oldDishName = userWeeklyDietMap.get(dishKey).get(dishIndex).getName();

        Random rand = new Random();
        List<DishNutriwi> dishesList = getDishListByProfile(profileEnum);
        DishNutriwi newDish = dishesList.get(rand.nextInt(dishesList.size()));

        while (newDish.getName().equals(oldDishName)) {
            newDish = dishesList.get(rand.nextInt(dishesList.size()));
        }

        userWeeklyDietMap.get(dishKey).set(dishIndex, newDish);
        String weeklyDietString = weeklyDietMapToString(userWeeklyDietMap);

        savDietToDB(username, weeklyDietString);
        return weeklyDietString;
    }


    private void savDietToDB(String username, String weeklyDietString) {
        User admin = userRepository.getOne(username);
        ProductCart productCart = admin.getProductCart();

        productCart.setWeeklyDiet(weeklyDietString);
        userRepository.save(admin);
    }


    /**
     * Add a dish to the json dish list
     *
     * @param dishNutriwi
     * @return
     */
    public Boolean addDish(DishNutriwi dishNutriwi) {
        return dietDAO.addDishToDietList(dishNutriwi);
    }


}
