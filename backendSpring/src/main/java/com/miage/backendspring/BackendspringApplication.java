package com.miage.backendspring;

import com.miage.backendspring.config.DishJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@EnableCaching
public class BackendspringApplication {

    @Autowired
    DishJsonParser dishJsonParser;

    public static void main(String[] args) {
        SpringApplication.run(BackendspringApplication.class, args);
    }

    @PostConstruct
    public void init() {
        dishJsonParser.parseJson();
    }

}
