package com.miage.backendspring.entity;

public enum Profil {

     VEGETARIAN ("Végétarien"),
     GLUTEN_FREE ("Sans gluten"),
     LACTOSE_FREE ("Sans lactose");

     private String displayName;

     Profil(String displayName) {
          this.displayName = displayName;
     }

     public String displayName() { return displayName; }

     @Override
     public String toString() { return displayName; }

}
