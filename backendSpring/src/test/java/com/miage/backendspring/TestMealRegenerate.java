package com.miage.backendspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.backendspring.dao.DietDAO;
import com.miage.backendspring.dao.UserRepository;
import com.miage.backendspring.entity.ProductCart;
import com.miage.backendspring.entity.User;
import com.miage.backendspring.service.DietService;
import com.miage.backendspring.service.profiles.ProfileEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TestMealRegenerate {

    @Test
    void contextLoads() {
        /*Profile p = new Profile(18, 70, ProfileEnum.CHOLESTEROL);
        p.setNutritionalQualities(p.getProfileType());
        p.setAllergens(AllergenEnum.EGG_FREE); */


        final ObjectMapper objectMapper = new ObjectMapper();
        DietService dietService = new DietService(new DietDAO(objectMapper, null), new UserRepository() {
            User admin = new User("admin", "password", new ProductCart());
            @Override
            public List<User> findAll() {
                return null;
            }

            @Override
            public List<User> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<User> findAllById(Iterable<String> iterable) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends User> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<User> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public User getOne(String s) {
                return admin;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> S save(S s) {
                return null;
            }

            @Override
            public Optional<User> findById(String s) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(User user) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends User> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends User> boolean exists(Example<S> example) {
                return false;
            }
        });

        //------------------test pour un compte admin avec un profile GLUTEN_FREE sur le jour_3 de la semaine----------------------------------------
        //dans le front il faudra récupérer le jour "cliqué" pour la key et
        //la value 0 ou 1 dans la liste pour savoir si c'est le dejeuner ou le diner

        //Map<String, List<DishNutriwi>> dietMap = dietService.getWeeklyDiet(ProfileEnum.GLUTEN_FREE);
        System.out.println("Get Diet test : "/*+dietMap*/);
        String oldWeeklyDiet = dietService.getWeeklyDiet("admin", false, ProfileEnum.GLUTEN_FREE);
        System.out.println("\n TEST DU OLD DIET -------> \n \n" + oldWeeklyDiet);
        String updatedWeeklyDiet = dietService.getRegenerateDishByProfile("admin", "Jour_3", 0, ProfileEnum.GLUTEN_FREE);
        System.out.println("\n TEST DU NEW DIET (DISHKEY : DISHINDEX doit etre modifié) -------> \n \n" +updatedWeeklyDiet); //test OK
        //System.out.println("0=CHAINE EGALE, autre valeur = different ---> "+oldWeeklyDiet.compareTo(updatedWeeklyDiet));
    }
}