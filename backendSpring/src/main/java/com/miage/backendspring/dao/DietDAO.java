package com.miage.backendspring.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.backendspring.entity.diet.DishNutriwi;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DietDAO {

    private final ObjectMapper objectMapper;

    /**
     * Get weekly diet list from json file and cache it
     * @return
     */
    @Cacheable("dietDB")
    public List<DishNutriwi> getDietList() {
        TypeReference<List<DishNutriwi>> typeReference = new TypeReference<List<DishNutriwi>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/nutriwiClean.json");

        List<DishNutriwi> response = null;

        try {
            response = objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


    /**
     * Add a dish to the list and write it in the file
     * @param dishNutriwi
     * @return
     */
    public Boolean addDishToDietList(DishNutriwi dishNutriwi) {
        List<DishNutriwi> dietList = this.getDietList();
        dietList.add(dishNutriwi);

        boolean status = false;

        if (dietList.contains(dishNutriwi)){
            try {
                objectMapper.writeValue(new File("src/main/resources/nutriwiClean.json"), dietList);
                status = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

}