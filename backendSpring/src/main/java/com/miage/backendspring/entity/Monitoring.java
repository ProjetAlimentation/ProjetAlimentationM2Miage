package com.miage.backendspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
/*
    @ManyToOne
    @JoinColumn(name="id")
    private User user;
*/


}
