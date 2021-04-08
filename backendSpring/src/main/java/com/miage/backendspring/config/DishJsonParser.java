package com.miage.backendspring.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.miage.backendspring.entity.diet.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DishJsonParser {

    private final ObjectMapper objectMapper;


    @Cacheable("dietDB")
    public List<Dish> parseJson(){
        TypeReference<List<Dish>> typeReference = new TypeReference<List<Dish>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/dataClean.json");

        List<Dish> response = null;

        try {
            //SimpleModule module = new SimpleModule("CustomJsonDeserializer", new Version(1, 0, 0, null, null, null));
            //module.addDeserializer(Dish.class, new CustomJsonDeserializer());
            //objectMapper.registerModule(module);
            response = objectMapper.readValue(inputStream, typeReference);
            //System.out.println(response.toString());
            String s = objectMapper.writeValueAsString(response);
            System.out.println(s);
            //BufferedWriter writer = new BufferedWriter(new FileWriter("dataClean.json"));
            //writer.write(s);
            //writer.close();

        /*    List<Dish2> dishes = new ArrayList<>();
            for (Dish d: response) {
                TypeReference<List<Dish2.Ingredient>> typeReferenceNode = new TypeReference<List<Dish2.Ingredient>>(){};
                TypeReference<LinkedList<Dish2.PreparationSteps>> typeReferencePrepNode = new TypeReference<LinkedList<Dish2.PreparationSteps>>(){};

                List<Dish2.Ingredient> ingredients = objectMapper.readValue(d.getIngredients(), typeReferenceNode);
                LinkedList<Dish2.PreparationSteps> preparationSteps = objectMapper.readValue(d.getPreparationSteps(), typeReferencePrepNode);

                Dish2 dish = Dish2.builder().description(d.getDescription())
                        .imageFileName(d.getImageFileName())
                        .name(d.getName())
                        .type(d.getType())
                        .nbPersons(d.getNbPersons())
                        .preparationTime(d.getPreparationTime())
                        .ingredients(ingredients.stream().map(Dish2.Ingredient::getIngredient).collect(Collectors.toList()))
                        .preparationSteps(preparationSteps.stream().map(Dish2.PreparationSteps::getPreparation_step).collect(Collectors.toList()))
                        .build();

                dishes.add(dish);
            }
            System.out.println(dishes.toString());
            BufferedWriter writer = new BufferedWriter(new FileWriter("dataClean2.json"));
            String s = objectMapper.writeValueAsString(dishes);
            writer.write(s);
            writer.close();*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
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