package com.miage.backendspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum ProfileEnum {

     VEGETARIAN ("Végétarien"),
     GLUTEN_FREE ("Sans gluten"),
     LACTOSE_FREE ("Sans lactose");

     private String displayName;

     public String displayName() { return displayName; }

     @Override
     public String toString() { return displayName; }

}
