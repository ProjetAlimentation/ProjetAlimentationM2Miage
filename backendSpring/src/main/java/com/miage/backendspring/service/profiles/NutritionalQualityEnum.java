package com.miage.backendspring.service.profiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NutritionalQualityEnum {
    NUTRI_SCORE("Bonne qualité nutritionnelle (Nutri-Score)"),
    LOW_SALT ("Sel en faible quantité"),
    LOW_SUGAR ("Sucres en faibles quantité"),
    LOW_FAT ("Matières grasses en faible quantité"),
    LOW_SATURATED_FATTY_ACIDS ("Acides gras saturés en faible quantité");

    private final String displayName;

    @Override
    public String toString() { return displayName; }
}

