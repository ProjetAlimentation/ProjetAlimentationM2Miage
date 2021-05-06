package com.miage.backendspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "MONITORING")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Monitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_moni;

    private Double weight;
    private Integer mental;
    private Integer diet;
    private LocalDate date;


    @ManyToOne
    @JoinColumn(name="username", nullable=false)
    @JsonIgnore
    private User user;

}

