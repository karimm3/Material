package org.example.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MaterialRequest {

    @NotBlank(message = "Material name is required")
    private String materialName;

    @NotNull(message = "Material rate is required")
    @Positive(message = "Material rate must be positive")
    private BigDecimal materialRatePerPack;

    @NotNull(message = "Material pack size is required")
    @Positive(message = "Material pack size must be positive")
    private BigDecimal materialPackSize;


    // This field remains optional as descriptions often are.
    private String materialDesc;

    @NotBlank(message = "UOM ID is required")
    private String materialUomld;

    @NotBlank(message = "Type ID is required")
    private String materialTypeld;

    @NotBlank(message = "Manufacturer ID is required")
    private String materialManufacturerld;

    @NotBlank(message = "Vendor ID is required")
    private String materialVendorld;


}
