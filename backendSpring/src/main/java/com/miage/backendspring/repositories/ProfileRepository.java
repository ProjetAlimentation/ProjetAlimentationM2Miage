package com.miage.backendspring.repositories;

import com.miage.backendspring.entity.Monitoring;
import com.miage.backendspring.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository  extends CrudRepository<Profile, Long> {
}