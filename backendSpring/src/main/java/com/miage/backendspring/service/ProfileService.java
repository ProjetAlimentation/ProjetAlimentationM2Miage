package com.miage.backendspring.service;

import com.miage.backendspring.entity.Profile.Allergens;
import com.miage.backendspring.entity.Profile.NutritionalQuality;
import com.miage.backendspring.entity.Profile;
import com.miage.backendspring.repositories.AllergenRepository;
import com.miage.backendspring.repositories.NutritionalQualityRepository;
import com.miage.backendspring.repositories.ProfileRepository;
import com.miage.backendspring.service.profiles.AllergenEnum;
import com.miage.backendspring.service.profiles.ProfileEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

import static com.miage.backendspring.service.profiles.NutritionalQualityEnum.*;
import static com.miage.backendspring.service.profiles.NutritionalQualityEnum.LOW_SATURATED_FATTY_ACIDS;

@RequiredArgsConstructor
@Component
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AllergenRepository  allergenRepository;
    private final NutritionalQualityRepository  nutritionalQualityRepository;

    public boolean createAndSaveProfile(int age, int weight, ProfileEnum profileTypes, Set<AllergenEnum> allergens) {

        boolean add = false;
        Profile profile = new Profile();
        profile.setAge(age);
        profile.setWeight(weight);

        try {
        if(allergens != null && allergens.size() >0)
        {
            for (Enum<AllergenEnum> a : allergens)
            {
                Allergens allergen = new Allergens();
                allergen.setName(a);
                allergen.setIsAllergic(true);
                profile.setAllergens(allergen);
                allergenRepository.save(allergen);

            }
        }
      /*  if(profileTypes != null && profileTypes.size() >0)
        {
            for (Enum<ProfileEnum> profileType : profileTypes) {*/
                switch (profileTypes.name()) {
                    case "CHOLESTEROL":
                        NutritionalQuality nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(LOW_SALT);
                        nutriQuality.setQualityValue(3);
                        profile.addNutritionalQualities(nutriQuality);
                        nutritionalQualityRepository.save(nutriQuality);
                        

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
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);

                        nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(LOW_SUGAR);
                        nutriQuality.setQualityValue(3);
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);

                        nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(LOW_FAT);
                        nutriQuality.setQualityValue(1);
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);

                        nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(LOW_SATURATED_FATTY_ACIDS);
                        nutriQuality.setQualityValue(0);
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);

                        break;

                    case "HIGH_BLOOD_PRESSURE":

                        nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(LOW_SALT);
                        nutriQuality.setQualityValue(2);
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);

                        nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(LOW_SUGAR);
                        nutriQuality.setQualityValue(1);
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);

                        nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(LOW_FAT);
                        nutriQuality.setQualityValue(2);
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);

                        nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(LOW_SATURATED_FATTY_ACIDS);
                        nutriQuality.setQualityValue(1);
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);

                        break;
                    default:
                        nutriQuality = new NutritionalQuality();
                        nutriQuality.setName(NUTRI_SCORE);
                        nutriQuality.setQualityValue(3);
                        nutriQuality.setProfile(profile);
                        profile.addNutritionalQualities(nutriQuality);
                }
      /*      }
        }*/


            profileRepository.save(profile);
/*
            allergen.setProfile(profile);
            allergenRepository.save(allergen);
*/
            add= true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return add;
    }



}
