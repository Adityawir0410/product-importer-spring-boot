package com.example.product_importer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter 
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String handle;
    private String vendor;
    private String imageUrl;
    private BigDecimal price;

    public Product(String name, String handle, String vendor, String imageUrl, BigDecimal price) {
        this.name = name;
        this.handle = handle;
        this.vendor = vendor;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}