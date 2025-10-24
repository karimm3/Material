package org.example.Entity;

//package com.example.materialcrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
//@Table(name = "MaterialTypes")
public class MaterialType {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String materialTypeld; // Using the provided String ID as PK

//    @Column(unique = true)
    private String materialTypeName;
    private String materialTypeDesc;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Material> materials;

    //    @JsonIgnore // Prevents infinite loops during JSON serialization
//    @OneToOne(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "material_id", unique = true)
//    private Material material;

}
