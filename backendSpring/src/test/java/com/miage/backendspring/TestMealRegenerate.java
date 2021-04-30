package com.miage.backendspring;

import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.dao.UserRepository;
import com.miage.backendspring.entity.ProductCart;
import com.miage.backendspring.entity.User;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.service.DietService;
import com.miage.backendspring.service.profiles.ProfileEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TestMealRegenerate {


    @Mock
    UserRepository userRepository;

    @Mock
    DietDAO dietDAO;

    @InjectMocks
    DietService dietService;

    @Test
    void contextLoads() {
        User u = new User();
        u.setUsername("admin");
        u.setProductCart(ProductCart.builder().build());

        String dishKey = "Jour_3";
        int dejeunerIndex = 0, dinerIndex = 1;

        when(userRepository.getOne("admin")).thenReturn(u);
        List<DishNutriwi> dishes = Arrays.asList(
                DishNutriwi.builder().name("Curry de lentilles aux carottes").profile(Arrays.asList(ProfileEnum.GLUTEN_FREE.toString(),ProfileEnum.VEGETARIAN.toString())).build(),
                DishNutriwi.builder().name("Pizza végétarienne maison fraiche et bio sans additifs").profile(Arrays.asList(ProfileEnum.VEGETARIAN.toString())).build(),
                DishNutriwi.builder().name("dish3").profile(Arrays.asList(ProfileEnum.VEGETALIEN.toString(),ProfileEnum.GLUTEN_FREE.toString())).build(),
                DishNutriwi.builder().name("dish4").profile(Arrays.asList()).build()
        );

        when(dietDAO.getDietDishList()).thenReturn(dishes);

        String oldWeeklyDiet = dietService.getWeeklyDiet(u.getUsername(), false, ProfileEnum.GLUTEN_FREE);
        Map<String, List<DishNutriwi>> oldDietMap = dietService.weeklyDietStringToMap(oldWeeklyDiet);

        //ici je teste qu'au jour 3 il n'y ai pas de repas autre que GLUTEN_FREE (couverture de la plupart des méthodes de dietService)
        Assertions.assertNotEquals(dishes.get(1), oldDietMap.get(dishKey).get(dejeunerIndex));
        Assertions.assertNotEquals(dishes.get(1), oldDietMap.get(dishKey).get(dinerIndex));

        String updatedWeeklyDiet = dietService.getRegenerateDishByProfile(u.getUsername(), dishKey, dejeunerIndex, ProfileEnum.GLUTEN_FREE);
        Map<String, List<DishNutriwi>> newDietMap = dietService.weeklyDietStringToMap(updatedWeeklyDiet);

        //comparaison des deux maps, ne doivent pas être identique après le changement sur le jour 3
        Assertions.assertNotEquals(oldDietMap, newDietMap);

        //comparaison du dejeuner au jour dishKey : ne doivent pas être identique après avoir fait un getRegenerateDishByProfile
        Assertions.assertNotEquals(oldDietMap.get(dishKey).get(dejeunerIndex), newDietMap.get(dishKey).get(dejeunerIndex));

        //comparaison d'un dejeuner à un jour different de dishKey : doivent être identique car le jour est différent du dishKey passé en paramètre de getRegenerateDishByProfile
        Assertions.assertEquals(oldDietMap.get("Jour_4").get(dejeunerIndex), newDietMap.get("Jour_4").get(dejeunerIndex));
    }
}