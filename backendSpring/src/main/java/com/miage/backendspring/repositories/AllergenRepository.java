package com.miage.backendspring.repositories;

import com.miage.backendspring.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface AllergenRepository  extends CrudRepository<Profile.Allergens, Long> {

}
