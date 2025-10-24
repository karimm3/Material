package org.example.Dto;

import lombok.Data;

@Data
public class MaterialListingDto {

    private Integer Page;
    private Integer size;
     private String SortBy;
    private String SortOrder;

private SearchDto search;
}
