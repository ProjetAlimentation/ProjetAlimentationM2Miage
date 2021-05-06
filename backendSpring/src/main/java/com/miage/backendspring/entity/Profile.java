package com.miage.backendspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miage.backendspring.service.profiles.AllergenEnum;
import com.miage.backendspring.service.profiles.NutritionalQualityEnum;
import com.miage.backendspring.service.profiles.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static com.miage.backendspring.service.profiles.NutritionalQualityEnum.*;

@Entity(name = "PROFILE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Integer age;
    private Double weight;

    private String profileType;
    private String allergen;
    private String regime;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username")
    private User user;
}
