package com.miage.backendspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "MONITORING")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_moni;

    private double weight;
    private int mental;
    private int diet;
    private LocalDate date;
/*
    @ManyToOne
    @JoinColumn(name="id")
    private User user;
*/


}
