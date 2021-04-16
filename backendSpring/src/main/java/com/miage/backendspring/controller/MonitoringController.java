package com.miage.backendspring.controller;

import com.miage.backendspring.service.MonitoringService;
import com.miage.backendspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @GetMapping("/addMonitoring")
    public boolean addMonitoring(@RequestParam("weight") double weight, @RequestParam("mental") int mental, @RequestParam("diet") int diet) {
        return monitoringService.createAndSaveMonitoring(weight, mental, diet);
    }

}
