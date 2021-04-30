package com.miage.backendspring;

import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.dao.UserRepository;
import com.miage.backendspring.entity.User;
import com.miage.backendspring.service.DietService;
import com.miage.backendspring.service.profiles.ProfileEnum;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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
        /*Profile p = new Profile(18, 70, ProfileEnum.CHOLESTEROL);
        p.setNutritionalQualities(p.getProfileType());
        p.setAllergens(AllergenEnum.EGG_FREE); */

        User u = new User();
        u.setUsername("fsfds");

        when(userRepository.getOne("admin")).thenReturn(u);

        //------------------test pour un compte admin avec un profile GLUTEN_FREE sur le jour_3 de la semaine----------------------------------------
        //dans le front il faudra récupérer le jour "cliqué" pour la key et
        //la value 0 ou 1 dans la liste pour savoir si c'est le dejeuner ou le diner

        //Map<String, List<DishNutriwi>> dietMap = dietService.getWeeklyDiet(ProfileEnum.GLUTEN_FREE);
        System.out.println("Get Diet test : "/*+dietMap*/);
        String oldWeeklyDiet = dietService.getWeeklyDiet("admin", false, ProfileEnum.GLUTEN_FREE);
        System.out.println("\n TEST DU OLD DIET -------> \n \n" + oldWeeklyDiet);
        String updatedWeeklyDiet = dietService.getRegenerateDishByProfile("admin", "Jour_3", 0, ProfileEnum.GLUTEN_FREE);
        System.out.println("\n TEST DU NEW DIET (DISHKEY : DISHINDEX doit etre modifié) -------> \n \n" +updatedWeeklyDiet); //test OK
        //System.out.println("0=CHAINE EGALE, autre valeur = different ---> "+oldWeeklyDiet.compareTo(updatedWeeklyDiet));
    }
}