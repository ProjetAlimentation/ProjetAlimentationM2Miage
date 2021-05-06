package com.miage.backendspring;

import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.entity.ProductCart;
import com.miage.backendspring.entity.Profile;
import com.miage.backendspring.entity.User;
import com.miage.backendspring.entity.diet.DishNutriwi;
import com.miage.backendspring.repositories.UserRepository;
import com.miage.backendspring.service.DietService;
import com.miage.backendspring.service.profiles.AllergenEnum;
import com.miage.backendspring.service.profiles.RegimeEnum;
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
public class TestDietService {


    @Mock
    UserRepository userRepository;

    @Mock
    DietDAO dietDAO;

    @InjectMocks
    DietService dietService;

    @Test
    void contextLoads() { //test de regeneration de repas au jour 3 pour le dejeuner pour un profil avec allergen = gluten_free
        User u = new User();
        u.setUsername("admin");
        u.setProductCart(ProductCart.builder().build());

        String dishKey = "Jour_3";
        int dejeunerIndex = 0, dinerIndex = 1;

        Profile profile = new Profile();
        //profile.setUser(u);
        profile.setAllergen(AllergenEnum.GLUTEN_FREE.name());
        profile.setRegime(null);
        u.setProfile(profile);

        when(userRepository.getOne("admin")).thenReturn(u);
        List<DishNutriwi> dishes = Arrays.asList(
                DishNutriwi.builder().name("Curry de lentilles aux carottes").profile(Arrays.asList(AllergenEnum.GLUTEN_FREE.toString(),RegimeEnum.VEGETARIAN.toString())).build(),
                DishNutriwi.builder().name("Pizza végétarienne maison fraiche et bio sans additifs").profile(Arrays.asList(RegimeEnum.VEGETARIAN.toString())).build(),
                DishNutriwi.builder().name("dish3").profile(Arrays.asList(RegimeEnum.VEGETALIEN.toString(),AllergenEnum.GLUTEN_FREE.toString())).build(),
                DishNutriwi.builder().name("dish4").profile(Arrays.asList()).build()
        );

        when(dietDAO.getDietDishList()).thenReturn(dishes);

        System.out.println(dietService.getDishListByProfile(u.getUsername()));

        String oldWeeklyDiet = dietService.getWeeklyDiet(u.getUsername(), false);
        Map<String, List<DishNutriwi>> oldDietMap = dietService.weeklyDietStringToMap(oldWeeklyDiet);

        //ici je test qu'au jour 3 il n'y ai pas de repas autre que GLUTEN_FREE (couverture de la plupart des méthodes de dietService)
        Assertions.assertNotEquals(dishes.get(1), oldDietMap.get(dishKey).get(dejeunerIndex));
        Assertions.assertNotEquals(dishes.get(1), oldDietMap.get(dishKey).get(dinerIndex));

        String updatedWeeklyDiet = dietService.getRegenerateDishByProfile(u.getUsername(), dishKey, dejeunerIndex);
        Map<String, List<DishNutriwi>> newDietMap = dietService.weeklyDietStringToMap(updatedWeeklyDiet);

        //comparaison des deux maps, ne doivent pas être identique après le changement sur le jour 3
        Assertions.assertNotEquals(oldDietMap, newDietMap);

        //comparaison du dejeuner au jour dishKey : ne doivent pas être identique après avoir fait un getRegenerateDishByProfile
        Assertions.assertNotEquals(oldDietMap.get(dishKey).get(dejeunerIndex), newDietMap.get(dishKey).get(dejeunerIndex));

        //comparaison d'un dejeuner à un jour different de dishKey : doivent être identique car le jour est différent du dishKey passé en paramètre de getRegenerateDishByProfile
        Assertions.assertEquals(oldDietMap.get("Jour_4").get(dejeunerIndex), newDietMap.get("Jour_4").get(dejeunerIndex));
    }
}