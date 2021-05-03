package com.miage.backendspring.controller;

import com.miage.backendspring.service.MonitoringService;
import com.miage.backendspring.service.ProfileService;
import com.miage.backendspring.service.profiles.AllergenEnum;
import com.miage.backendspring.service.profiles.ProfileEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class Profile_Controller {


    private final ProfileService profileService;

    @GetMapping("/addProfile")

        public boolean addProfile(@RequestParam("age") int age, @RequestParam("weight") int weight, @RequestParam("profileType") Enum<ProfileEnum>  profileType, @RequestParam("allergens") Set<Enum<AllergenEnum>> allergens) {
        return profileService.createAndSaveProfile(age, weight, profileType, allergens);
    }

}
