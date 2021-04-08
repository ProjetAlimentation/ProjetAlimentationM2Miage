package com.miage.backendspring.config;

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
public class DishJsonParser {

    private final ObjectMapper objectMapper;

   /* public List<Dish3> parseJson() {
        TypeReference<List<Dish3>> typeReference = new TypeReference<List<Dish3>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/nutriwiClean.json");

        List<Dish3> response = null;

        try {
            //SimpleModule module = new SimpleModule("CustomJsonDeserializer", new Version(1, 0, 0, null, null, null));
            //module.addDeserializer(Dish.class, new CustomJsonDeserializer());
            //objectMapper.registerModule(module);
            response = objectMapper.readValue(inputStream, typeReference);
            //System.out.println(response.toString());
            //String s = objectMapper.writeValueAsString(response);
            //System.out.println(s);
            //BufferedWriter writer = new BufferedWriter(new FileWriter("dataClean.json"));
            //writer.write(s);
            //writer.close();

            List<Dish2> dishes = new ArrayList<>();
            for (Dish3 d : response) {
                TypeReference<List<Dish4.Ingredient>> typeReferenceNodeIng = new TypeReference<List<Dish4.Ingredient>>() {
                };
                TypeReference<LinkedList<Dish4.PreparationStep>> typeReferencePrepNode = new TypeReference<LinkedList<Dish4.PreparationStep>>() {
                };
                TypeReference<LinkedList<Dish4.Profile>> typeReferenceProfile = new TypeReference<LinkedList<Dish4.Profile>>() {
                };
                TypeReference<LinkedList<Dish4.Nutrition>> typeReferenceNutrition = new TypeReference<LinkedList<Dish4.Nutrition>>() {
                };

                List<Dish4.Ingredient> ingredients = objectMapper.readValue(d.getIngredient(), typeReferenceNodeIng);
                List<Dish4.PreparationStep> preparationSteps = objectMapper.readValue(d.getPreparationStep(), typeReferencePrepNode);
                List<Dish4.Profile> profiles = objectMapper.readValue(d.getProfile(), typeReferenceProfile);
                List<Dish4.Nutrition> nutritions = objectMapper.readValue(d.getNutrition(), typeReferenceNutrition);

                Dish2 dish = Dish2.builder()
                        .imageSrc(d.getImageSrc())
                        .name(d.getName())
                        .portion(d.getPortions())
                        .prepTime(d.getPrepTime())
                        .ingredients(ingredients.stream().map(Dish4.Ingredient::getIngredient).collect(Collectors.toList()))
                        .preparationSteps(preparationSteps.stream().map(Dish4.PreparationStep::getPreparationStep).collect(Collectors.toList()))
                        .profile(profiles.stream().map(Dish4.Profile::getProfile).collect(Collectors.toList()))
                        .nutrition(nutritions.stream().map(Dish4.Nutrition::getNutrition).collect(Collectors.toList()))
                        .build();

                dishes.add(dish);
            }
            System.out.println(dishes.toString());
            BufferedWriter writer = new BufferedWriter(new FileWriter("nutriwiClean.json"));
            String s = objectMapper.writeValueAsString(dishes);
            writer.write(s);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }*/

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

    public Boolean addDishToDietList(DishNutriwi dishNutriwi) {
        List<DishNutriwi> dietList = this.getDietList();
        dietList.add(dishNutriwi);

        if (dietList.contains(dishNutriwi)){
            try {
                objectMapper.writeValue(new File("src/main/resources/nutriwiClean.json"), dietList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }else
            return false;

    }

   /* public class CustomJsonDeserializer extends StdDeserializer<Dish>{

        protected CustomJsonDeserializer(Class<?> vc) {
            super(vc);
        }

        public CustomJsonDeserializer() {
            this(null);
        }

        @Override
        public Dish deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Dish dish = new Dish();
            ObjectCodec codec = jsonParser.getCodec();
            JsonNode node = codec.readTree(jsonParser);

            // try catch block
            JsonNode nbPersonsNode = node.get("nb_persons");
            JsonNode prepTimeNode = node.get("prep_time");
            JsonNode dishTypeNode = node.get("dish_type");
            JsonNode nameNode = node.get("name");
            JsonNode descriptionNode = node.get("description");

            JsonNode preparationStepNode = node.get("preparation_step");
            JsonNode ingredientNode = node.get("ingredient");
            JsonNode imageNode = node.get("image");

            dish.setNbPersons(nbPersonsNode.asText());
            dish.setPreparationTime(prepTimeNode.asText());
            dish.setType(dishTypeNode.asText());
            dish.setName(nameNode.asText());
            dish.setDescription(descriptionNode.asText());

            dish.setIngredients(ingredientNode.asText());
            dish.setPreparationSteps(preparationStepNode.asText());
            dish.setImageFileName(imageNode.asText());
            return dish;
        }
    }*/

}