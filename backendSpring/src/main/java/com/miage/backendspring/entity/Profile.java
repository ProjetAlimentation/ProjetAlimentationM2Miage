package com.miage.backendspring.entity;

import com.miage.backendspring.service.profiles.AllergenEnum;
import com.miage.backendspring.service.profiles.NutritionalQualityEnum;
import com.miage.backendspring.service.profiles.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static com.miage.backendspring.service.profiles.NutritionalQualityEnum.*;

@Entity(name = "PROFILE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_profil;

    private int age;
    private int weight;
    //private EnumSet<ProfileEnum> profilesType;
    private Enum<ProfileEnum> profileType;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "profile")
    private Set<Allergens> allergens = new HashSet<Allergens>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "profile")
    private Set<NutritionalQuality> nutritionalQualites = new HashSet<NutritionalQuality>();

    public Profile(int age, int weight, Enum<ProfileEnum> profileType){
        this.age = age;
        this.weight = weight;
        this.profileType = profileType;
    }

    public void setAllergens (Allergens allergen){
        this.allergens.add(allergen);
    }

    public void addNutritionalQualities(NutritionalQuality nutriQuality){

        this.nutritionalQualites.add(nutriQuality);
    }

    @Entity(name = "Allergens")
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class Allergens {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id_aller;
        private Enum<AllergenEnum> name;
        private Boolean isAllergic = true;
        @ManyToOne(cascade=CascadeType.ALL)
        Profile profile;

    }

    @Entity(name = "NutritionalQuality")
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class NutritionalQuality {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id_NNutri;
        private Enum<NutritionalQualityEnum> name;
        private int qualityValue; //from 0 to 3 (0:pas important, 1:important, 2:très important, 3:obligatoire)
        @ManyToOne(cascade=CascadeType.ALL)
                Profile profile;

    }
}
