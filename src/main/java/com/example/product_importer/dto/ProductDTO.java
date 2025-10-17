package com.example.product_importer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    private String title;
    private String handle;
    private String vendor;
    private List<ImageDTO> images;
    private List<VariantDTO> variants;
}