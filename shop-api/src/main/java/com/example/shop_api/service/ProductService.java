    package com.example.shop_api.service;

    import com.example.shop_api.exception.ProductNotFoundException;
    import com.example.shop_api.model.Product;
    import com.example.shop_api.repository.ProductRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class ProductService {

        private final ProductRepository productRepository;

        public Page<Product> getAllProducts(Pageable pageable) {
            return productRepository.findAll(pageable);
        }

        public Product getProductById(Long id) {
            return productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
        }
    }
