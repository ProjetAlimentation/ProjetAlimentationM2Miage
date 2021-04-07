package com.example.datarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EntityA")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntityA {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "AKEY")
    private String aKey;
    @Column(name = "ENTITY_AVAR2")
    private String entityAVar2;
    @Column(name = "ENTITY_AVAR3")
    private String entityAVar3;
    @Column(name = "ENTITY_AVAR4")
    private String entityAVar4;
}
