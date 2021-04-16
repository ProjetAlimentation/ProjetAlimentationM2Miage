package com.miage.backendspring.service;

import com.miage.backendspring.entity.Monitoring;
import com.miage.backendspring.repositories.MonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MonitoringService {
    private final MonitoringRepository monitoringRepository;

    public boolean createAndSaveMonitoring(double weight, int mental) {

        boolean add = false;
        Monitoring monitoring = new Monitoring();
        monitoring.setWeight(weight);
        monitoring.setMental(mental);

        try {
            monitoringRepository.save(monitoring);
            add= true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return add;
    }


}
