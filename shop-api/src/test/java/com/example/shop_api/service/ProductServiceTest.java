package com.example.shop_api.service;

import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.model.Product;
import com.example.shop_api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Test Product 1");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Test Product 2");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(Arrays.asList(p1, p2), pageable, 2);

        when(productRepository.findAll(pageable)).thenReturn(page);

        Page<Product> result = productService.getAllProducts(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals("Test Product 1", result.getContent().get(0).getName());

        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetProductByIdFound() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(p1));

        Product product = productService.getProductById(1L);

        assertNotNull(product);
        assertEquals("Test Product", product.getName());

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(99L));

        verify(productRepository, times(1)).findById(99L);
    }
}
