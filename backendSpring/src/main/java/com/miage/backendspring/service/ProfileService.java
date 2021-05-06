package com.miage.backendspring.service;


import com.miage.backendspring.entity.Profile;
import com.miage.backendspring.repositories.ProfileRepository;
import com.miage.backendspring.service.profiles.AllergenEnum;
import com.miage.backendspring.service.profiles.ProfileEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.miage.backendspring.service.profiles.NutritionalQualityEnum.*;
import static com.miage.backendspring.service.profiles.NutritionalQualityEnum.LOW_SATURATED_FATTY_ACIDS;

@RequiredArgsConstructor
@Component
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MonitoringService monitoringService;

    public void createAndSaveProfile(Profile profile) {
        profileRepository.save(profile);
        monitoringService.createAndSaveMonitoring(profile.getWeight(),2,2);

    }

        /*public boolean createAndSaveProfile(int age, int weight, Enum<ProfileEnum> profileType, Set<Enum<AllergenEnum>> allergens) {

        boolean add = false;
        Profile profile = new Profile();
        profile.setAge(age);
        profile.setWeight(weight);

        if(allergens != null && allergens.size() >0)
        {
            for (Enum<AllergenEnum> a : allergens)
            {
                Allergens allergen = new Allergens();
                allergen.setName(a);
                allergen.setIsAllergic(true);
                profile.setAllergens(allergen);
            }
        }

        switch (profileType.name()) {
            case "CHOLESTEROL":
                NutritionalQuality nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_SALT);
                nutriQuality.setQualityValue(3);
                profile.addNutritionalQualities(nutriQuality);

                NutritionalQuality nutriQuality2 = new NutritionalQuality();
                nutriQuality.setName(LOW_SUGAR);
                nutriQuality.setQualityValue(1);
                profile.addNutritionalQualities(nutriQuality2);

                NutritionalQuality nutriQuality3 = new NutritionalQuality();
                nutriQuality.setName(LOW_FAT);
                nutriQuality.setQualityValue(1);
                profile.addNutritionalQualities(nutriQuality3);

                NutritionalQuality nutriQuality4 = new NutritionalQuality();
                nutriQuality.setName(LOW_SATURATED_FATTY_ACIDS);
                nutriQuality.setQualityValue(2);
                profile.addNutritionalQualities(nutriQuality4);

                break;
            case "DIABETIC":

                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_SALT);
                nutriQuality.setQualityValue(2);
                profile.addNutritionalQualities(nutriQuality);

                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_SUGAR);
                nutriQuality.setQualityValue(3);
                profile.addNutritionalQualities(nutriQuality);

                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_FAT);
                nutriQuality.setQualityValue(1);
                profile.addNutritionalQualities(nutriQuality);

                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_SATURATED_FATTY_ACIDS);
                nutriQuality.setQualityValue(0);
                profile.addNutritionalQualities(nutriQuality);

                break;

            case "HIGH_BLOOD_PRESSURE":

                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_SALT);
                nutriQuality.setQualityValue(2);
                profile.addNutritionalQualities(nutriQuality);

                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_SUGAR);
                nutriQuality.setQualityValue(1);
                profile.addNutritionalQualities(nutriQuality);

                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_FAT);
                nutriQuality.setQualityValue(2);
                profile.addNutritionalQualities(nutriQuality);

                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(LOW_SATURATED_FATTY_ACIDS);
                nutriQuality.setQualityValue(1);
                profile.addNutritionalQualities(nutriQuality);

                break;
            default:
                nutriQuality = new NutritionalQuality();
                nutriQuality.setName(NUTRI_SCORE);
                nutriQuality.setQualityValue(3);
                profile.addNutritionalQualities(nutriQuality);
        }


        try {

            profileRepository.save(profile);
            add= true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return add;
    }
*/


    public Profile getProfile(String username){
        Profile profile = profileRepository.getProfileByUser_Username(username);

        Profile build = Profile.builder()
                .id(profile.getId())
                .age(profile.getAge())
                .weight(profile.getWeight())
                .allergen(profile.getAllergen())
                .regime(profile.getRegime())
                .profileType(profile.getProfileType())
                .build();

        return build;
    }


}
