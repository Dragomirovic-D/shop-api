package com.example.shop_api.controller;

import com.example.shop_api.model.Product;
import com.example.shop_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
@Tag(name = "Products", description = "Operations related to products")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Get all products",
            description = "List all products with pagination and sorting",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(schema = @Schema(example = "{ \"status\": 500, \"error\": \"Internal Server Error\", \"message\": \"Unexpected error\" }")))
            }
    )
    @GetMapping
    public Page<Product> getAllProducts(
            @PageableDefault(size = 10, sort = "id") Pageable pageable,
            @RequestParam(required = false) @Min(1) @Max(100) Integer size
    ) {
        if (size != null) {
            pageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());
        }
        return productService.getAllProducts(pageable);
    }

    @Operation(
            summary = "Get product by ID",
            description = "Retrieve a single product by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product found"),
                    @ApiResponse(responseCode = "404", description = "Product not found",
                            content = @Content(schema = @Schema(example = "{ \"status\": 404, \"error\": \"Not Found\", \"message\": \"Product with id 123 not found\" }"))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(schema = @Schema(example = "{ \"status\": 500, \"error\": \"Internal Server Error\", \"message\": \"Unexpected error\" }")))
            }
    )
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
