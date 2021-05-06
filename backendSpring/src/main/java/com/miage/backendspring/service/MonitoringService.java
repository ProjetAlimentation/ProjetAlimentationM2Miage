package com.miage.backendspring.service;

import com.miage.backendspring.entity.Monitoring;
import com.miage.backendspring.entity.User;
import com.miage.backendspring.repositories.MonitoringRepository;
import com.miage.backendspring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MonitoringService {
    private final MonitoringRepository monitoringRepository;
    private final UserRepository userRepository;

    public boolean createAndSaveMonitoring(String username, double weight, int mental, int diet) {

        boolean add = false;

        Monitoring monitoring = new Monitoring();
        monitoring.setWeight(weight);
        monitoring.setMental(mental);
        monitoring.setDiet(diet);
        monitoring.setDate(LocalDate.now());
        User one = userRepository.findById(username).get();
        monitoring.setUser(one);

        try {
            monitoringRepository.save(monitoring);
            add= true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return add;
    }


    public List<Monitoring> getMonitoring(String username){
        Iterable<Monitoring> all = monitoringRepository.findByUser_Username(username);
        return (List<Monitoring>) all;
    }


}
