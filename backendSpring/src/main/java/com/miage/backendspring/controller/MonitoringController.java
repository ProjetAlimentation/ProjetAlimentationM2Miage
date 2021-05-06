package com.miage.backendspring.controller;

import com.miage.backendspring.entity.Monitoring;
import com.miage.backendspring.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @PostMapping("/addMonitoring")
    public boolean addMonitoring(@RequestParam String username, @RequestParam("weight") double weight, @RequestParam("mental") int mental, @RequestParam("diet") int diet) {
        return monitoringService.createAndSaveMonitoring(username,weight, mental, diet);
    }


    @GetMapping("/getMonitoring")
    public ResponseEntity<List<Monitoring>> getMonitoring(@RequestParam String username){
        return ResponseEntity.ok(monitoringService.getMonitoring(username));
    }

}
