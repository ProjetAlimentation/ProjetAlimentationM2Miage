package com.miage.backendspring.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.backendspring.entity.Monitoring;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Component
@RequiredArgsConstructor
public class MonitoringDAO {

    private final EntityManager em;

    @Transactional
    public void addMonitoring( double mental, int weight)
    {

    }

}
