package com.miage.backendspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum ProfileEnum {

     //profil de test avec Nutriwi
     VEGETARIAN ("Végétarien"),
     VEGETALIEN ("Végétalien"),
     GLUTEN_FREE ("Sans gluten"),
     LACTOSE_FREE ("Sans lactose"),
     //ci dessous, la liste des "vrais" profils
     DIABETIC ("Diabétique"),
     CHOLESTEROL("Cholesterol"),
     HIGH_BLOOD_PRESSURE("Hypertension artérielle");

     private String displayName;

     @Override
     public String toString() { return displayName; }

}
