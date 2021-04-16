package com.miage.backendspring.service;

import com.miage.backendspring.entity.Monitoring;
import com.miage.backendspring.repositories.MonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class MonitoringService {
    private final MonitoringRepository monitoringRepository;

    public boolean createAndSaveMonitoring(double weight, int mental, int diet) {

        boolean add = false;
        Monitoring monitoring = new Monitoring();
        monitoring.setWeight(weight);
        monitoring.setMental(mental);
        monitoring.setDiet(diet);

        monitoring.setDate(LocalDate.now());

        try {
            monitoringRepository.save(monitoring);
            add= true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return add;
    }


}
