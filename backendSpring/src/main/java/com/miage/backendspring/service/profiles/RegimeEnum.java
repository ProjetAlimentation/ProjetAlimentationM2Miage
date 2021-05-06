package com.miage.backendspring.service.profiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RegimeEnum {

    VEGETARIAN ("Végétarien"),
    VEGETALIEN ("Végétalien");

    private final String displayName;



    @Override
    public String toString() { return displayName; }
}
