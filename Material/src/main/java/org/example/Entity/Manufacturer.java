package org.example.Entity;

//package com.example.materialcrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
//@Table(name = "Manufacturers")
public class Manufacturer {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String materialManufacturerld; // Using the provided String ID as PK

//    @Column(unique = true)
    private String materialManufacturerName;
    private String materialManufacturerContactPerson;
    private String materialManufacturerContactNumber;
    private String materialManufacturerEmail;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Material> materials;
//@JsonIgnore // Prevents infinite loops during JSON serialization
//@OneToOne(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
//@JoinColumn(name = "material_id", unique = true)
//private Material material;

}
