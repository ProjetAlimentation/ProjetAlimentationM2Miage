package com.miage.backendspring.entity.diet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DishNutriwi {

    private String name;
    private Integer portion;
    private Integer prepTime;

    private List<String> ingredients = new ArrayList<>();
    private List<String> profile = new ArrayList<>();
    private List<String> nutrition = new LinkedList<>();
    private List<String> preparationSteps = new LinkedList<>();
    private String imageSrc;

}
