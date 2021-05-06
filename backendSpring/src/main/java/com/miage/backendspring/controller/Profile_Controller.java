package com.miage.backendspring.controller;

import com.miage.backendspring.entity.Profile;
import com.miage.backendspring.service.MonitoringService;
import com.miage.backendspring.service.ProfileService;
import com.miage.backendspring.service.profiles.AllergenEnum;
import com.miage.backendspring.service.profiles.ProfileEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class Profile_Controller {


    private final ProfileService profileService;


    @PostMapping("/addProfile")
    public void addProfile(@RequestBody Profile profile) {
        profileService.createAndSaveProfile(profile);
    }


    @GetMapping("/getProfile")
    public Profile getProfile(@RequestParam String username) {
        return profileService.getProfile(username);
    }

}
