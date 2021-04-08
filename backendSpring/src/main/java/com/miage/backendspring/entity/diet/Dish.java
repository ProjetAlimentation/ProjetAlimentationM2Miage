package com.miage.backendspring.entity.diet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Dish {

    private String name;
    private String description;
    private String type;
    private String nbPersons;
    private String preparationTime;

    private List<String> ingredients = new ArrayList<>();
    private List<String> preparationSteps = new ArrayList<>();
    private String imageFileName;

}
