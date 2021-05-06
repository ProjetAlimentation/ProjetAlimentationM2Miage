package com.miage.backendspring.service.profiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AllergenEnum {
    GLUTEN_FREE ("Sans gluten"),
    LACTOSE_FREE ("Sans lactose"),
    EGG_FREE("Sans oeufs"),
    NUTS_FREE("Sans fruits à coque"),
    PEANUT_FREE("Sans arachides"),
    SOJA_FREE("Sans soja");

    private final String displayName;



    @Override
    public String toString() { return displayName; }
}
