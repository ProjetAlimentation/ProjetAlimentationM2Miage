package com.miage.backendspring;

import com.miage.backendspring.config.DishJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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

    @Configuration
    public class SpringFoxConfig {
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }
    }

}
