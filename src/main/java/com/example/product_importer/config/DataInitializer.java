package com.example.product_importer.config;

import com.example.product_importer.dto.ProductDTO;
import com.example.product_importer.dto.ProductResponse;
import com.example.product_importer.entity.Product;
import com.example.product_importer.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            System.out.println("Database is empty. Initializing data from URL...");

            final String URL = "https://famme.no/products.json";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                String jsonResponse = restTemplate.getForObject(URL, String.class);

                ProductResponse productData = objectMapper.readValue(jsonResponse, ProductResponse.class);

                List<Product> productsToSave = new ArrayList<>();

                for (ProductDTO dto : productData.getProducts()) {
                    String imageUrl = (!dto.getImages().isEmpty()) ? dto.getImages().get(0).getSrc() : null;

                    BigDecimal price = (!dto.getVariants().isEmpty()) 
                        ? new BigDecimal(dto.getVariants().get(0).getPrice()) 
                        : BigDecimal.ZERO;

                    Product product = new Product(
                            dto.getTitle(),
                            dto.getHandle(),
                            dto.getVendor(),
                            imageUrl,
                            price
                    );
                    productsToSave.add(product);
                }

                productRepository.saveAll(productsToSave);
                System.out.println("SUCCESS: " + productsToSave.size() + " products have been initialized!");

            } catch (Exception e) {
                System.err.println("ERROR: Failed to initialize data from JSON URL. " + e.getMessage());
            }
        } else {
            System.out.println("Database already contains data. Skipping initialization.");
        }
    }
}