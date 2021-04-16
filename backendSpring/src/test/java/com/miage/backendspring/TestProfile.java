package com.miage.backendspring;

import com.miage.backendspring.entity.AllergenEnum;
import com.miage.backendspring.entity.Profile;
import com.miage.backendspring.entity.ProfileEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestProfile {
    @Test
    void contextLoads() {
        Profile p = new Profile(18, 70, ProfileEnum.CHOLESTEROL);
        p.setNutritionalQualities(p.getProfileType());
        p.setAllergens(AllergenEnum.EGG_FREE);
    }
}