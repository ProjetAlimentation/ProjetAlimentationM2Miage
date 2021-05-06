package com.miage.backendspring.repositories;

import com.miage.backendspring.entity.Monitoring;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MonitoringRepository extends CrudRepository<Monitoring, Long> {


    List<Monitoring> findByUser_Username(String username);



}
