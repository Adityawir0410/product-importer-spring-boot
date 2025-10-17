package com.example.product_importer.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private List<ProductDTO> products;
}