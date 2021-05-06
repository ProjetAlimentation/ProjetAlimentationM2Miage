package com.miage.backendspring.service.profiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProfileEnum {

     //ci dessous, la liste des "vrais" profils
     DIABETIC ("Diabétique"),
     CHOLESTEROL("Cholesterol"),
     HIGH_BLOOD_PRESSURE("Hypertension artérielle");

     private final String displayName;

     @Override
     public String toString() { return displayName; }

}
