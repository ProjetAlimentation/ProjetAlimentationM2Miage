package com.miage.backendspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import static com.miage.backendspring.entity.NutritionalQualityEnum.*;

@Data
@AllArgsConstructor
public class Profile {

    private int age;
    private int weight;
    //private EnumSet<ProfileEnum> profilesType;
    private Enum<ProfileEnum> profileType;
    private Set<Allergens> allergens = new HashSet<>();
    private Set<NutritionalQuality> nutritionalQualites = new HashSet<>();

    public Profile(int age, int weight, Enum<ProfileEnum> profileType){
        this.age = age;
        this.weight = weight;
        this.profileType = profileType;
    }

    public void setAllergens (Enum<AllergenEnum> allergen){
        this.allergens.add(new Allergens(allergen, true));
    }

    public void setNutritionalQualities(Enum<ProfileEnum> profile){
        this.profileType = profile;
        this.nutritionalQualites.add(new NutritionalQuality(NUTRI_SCORE, 3)); //par défaut on sélectionne la plus haute valeur (3) nutritionnel du produit

        switch (profile.name()) {
            case "Cholesterol":
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SALT, 3));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SUGAR, 1));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_FAT, 1));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SATURATED_FATTY_ACIDS, 2));
                break;
            case "Diabétique":
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SALT, 2));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SUGAR, 3));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_FAT, 1));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SATURATED_FATTY_ACIDS, 0)); //au contraire ils sont protecteur
                break;
            case "Hypertension artérielle":
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SALT, 2));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SUGAR, 1));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_FAT, 2));
                this.nutritionalQualites.add(new NutritionalQuality(LOW_SATURATED_FATTY_ACIDS, 1));
                break;
        }

        //parcourir un enumset
        /*profilesType.allOf(ProfileEnum.class).forEach(displayName -> {
            switch (displayName.toString()) {
                case "Diabétique":
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SALT, 2));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SUGAR, 3));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_FAT, 1));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SATURATED_FATTY_ACIDS, 0)); //au contraire ils sont protecteur
                    break;
                case "Cholesterol":
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SALT, 3));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SUGAR, 1));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_FAT, 1));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SATURATED_FATTY_ACIDS, 2));
                    break;
                case "Hypertension artérielle":
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SALT, 2));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SUGAR, 1));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_FAT, 2));
                    this.nutritionalQualites.add(new NutritionalQuality(LOW_SATURATED_FATTY_ACIDS, 1));
                    break;
            }
        }); */
    }

    @AllArgsConstructor
    @Data
    public static class Allergens {
        private Enum<AllergenEnum> name;
        private Boolean isAllergic = true;
    }

    @AllArgsConstructor
    @Data
    public static class NutritionalQuality {
        private Enum<NutritionalQualityEnum> name;
        private int qualityValue; //from 0 to 3 (0:pas important, 1:important, 2:très important, 3:obligatoire)
    }
}
