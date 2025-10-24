package org.example.Entity;

//package com.example.materialcrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
//@Table(name = "Uoms")
public class Uom {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uomld; // Using the provided String ID as PK

//    @Column(unique = true)
    private String uomName;
    private String uomSymbol;

    // The "One" side of the One-to-Many relationship
    @OneToMany(mappedBy = "uom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Material> materials;

//    @JsonIgnore // Prevents infinite loops during JSON serialization
//    @OneToOne(mappedBy = "uom", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "material_id", unique = true)
//    private Material material;
}
