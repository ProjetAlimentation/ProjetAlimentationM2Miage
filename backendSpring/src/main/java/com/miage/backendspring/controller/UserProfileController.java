package com.miage.backendspring.controller;

import com.miage.backendspring.entity.Profile;
import com.miage.backendspring.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserProfileController {


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
