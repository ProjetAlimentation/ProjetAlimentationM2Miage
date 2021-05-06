package com.miage.backendspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "PROFILE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Integer age;
    private Double weight;

    private String profileType;
    private String allergen;
    private String regime;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username")
    private User user;
}
