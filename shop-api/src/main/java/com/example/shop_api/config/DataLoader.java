package com.example.shop_api.config;

import com.example.shop_api.model.Product;
import com.example.shop_api.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            InputStream inputStream = getClass().getResourceAsStream("/products.json");
            List<Product> products = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
            productRepository.saveAll(products);
            System.out.println("✅ Loaded " + products.size() + " products into H2 DB");
        }
    }
}
