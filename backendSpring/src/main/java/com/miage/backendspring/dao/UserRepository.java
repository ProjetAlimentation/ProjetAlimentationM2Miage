package com.miage.backendspring.dao;

import com.miage.backendspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
