package com.miage.backendspring.service;


import com.miage.backendspring.entity.Profile;
import com.miage.backendspring.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MonitoringService monitoringService;

    public void createAndSaveProfile(Profile profile) {
        profileRepository.save(profile);
        monitoringService.createAndSaveMonitoring(profile.getUser().getUsername(), profile.getWeight(),2,2);

    }

    public Profile getProfile(String username){
        Profile profile = profileRepository.getProfileByUser_Username(username);

        Profile build = Profile.builder()
                .id(profile.getId())
                .age(profile.getAge())
                .weight(profile.getWeight())
                .allergen(profile.getAllergen())
                .regime(profile.getRegime())
                .profileType(profile.getProfileType())
                .build();

        return build;
    }


}
