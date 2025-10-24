package org.example.Entity;



import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
//@EntityListeners(MaterialEntityListener.class) // Add this listener
//@Table(name = "materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Auto-generate UUID
    @Column(length = 36)
    private String id; // Changed from String materialld to UUID id

//    @Column(unique = true, updatable = false, length = 20) // Cannot be updated once set
//    private String externalId; // Renamed for clarity

    private String materialName;
    private BigDecimal materialRatePerPack;
    private BigDecimal materialPackSize;

    @Column(length = 1024)
    private String materialDesc;

    private boolean isActive;
    private String createdBy;
    private LocalDateTime createdOn;
    private String updatedBy;
    private LocalDateTime updatedOn;

    // --- Relationships ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uom_id")
    private Uom uom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private MaterialType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;


//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "uom_id")
//    private Uom uom;
//
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "type_id")
//    private MaterialType type;
//
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "manufacturer_id")
//    private Manufacturer manufacturer;
//
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "vendor_id")
//    private Vendor vendor;

    // --- Lifecycle Callbacks ---

}