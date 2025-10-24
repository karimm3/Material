package org.example.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MaterialDto {

//    @NotBlank(message = "materialld cannot be blank")
    private String materialld;
//
//    @NotBlank(message = "externalld cannot be blank")
//    private String externalld;

    private String materialName;
    private BigDecimal materialRatePerPack;
    private BigDecimal materialPackSize;
    private String materialDesc;

//    @NotNull
    private UomDto uom;

//    @NotNull
    private MaterialTypeDto type;

//    @NotNull
    private ManufacturerDto manufacturer;

//    @NotNull
    private VendorDto vendor;

    private boolean isActive;
    private String createdBy;
    private LocalDateTime createdOn;
    private String updatedBy;
    private LocalDateTime updatedOn;
}
