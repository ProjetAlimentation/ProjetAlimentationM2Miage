package com.miage.backendspring;

import com.miage.backendspring.dao.DietDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class BackendspringApplication {

    @Autowired
    DietDAO dietDAO;

    public static void main(String[] args) {
        SpringApplication.run(BackendspringApplication.class, args);
    }

    @PostConstruct
    public void init() {
        dietDAO.getDietList();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.miage.backendspring.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
