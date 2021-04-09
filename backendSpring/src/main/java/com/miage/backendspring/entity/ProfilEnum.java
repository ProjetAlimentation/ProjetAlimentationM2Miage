package com.miage.backendspring.entity;

public enum ProfilEnum {

     VEGETARIAN ("Végétarien"),
     GLUTEN_FREE ("Sans gluten"),
     LACTOSE_FREE ("Sans lactose");

     private String displayName;

     ProfilEnum(String displayName) {
          this.displayName = displayName;
     }

     public String displayName() { return displayName; }

     @Override
     public String toString() { return displayName; }

}
