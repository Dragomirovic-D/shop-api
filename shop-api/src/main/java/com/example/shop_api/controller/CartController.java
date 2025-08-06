package com.example.shop_api.controller;

import com.example.shop_api.model.CartItem;
import com.example.shop_api.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Operations related to shopping cart")
public class CartController {

    private final CartService cartService;


    /*
    @Operation(summary = "Add item to cart")
    @PostMapping("/add")
    public CartItem addToCart(@RequestParam Long productId,
                              @RequestParam @Min(1) int quantity) {
        return cartService.addToCart(productId, quantity);
    }

    @Operation(summary = "Get cart contents")
    @GetMapping
    public List<CartItem> getCart() {
        return cartService.getCart();
    }

    @Operation(summary = "Update cart item quantity")
    @PutMapping("/item/{id}")
    public CartItem updateCartItem(@PathVariable Long id,
                                   @RequestParam @Min(1) int quantity) {
        return cartService.updateCartItem(id, quantity);
    }

    @Operation(summary = "Remove item from cart")
    @DeleteMapping("/item/{id}")
    public void removeCartItem(@PathVariable Long id) {
        cartService.removeCartItem(id);
    }
*/

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam("productId") Long productId,
                              @RequestParam("quantity") @Min(1) int quantity) {
        return cartService.addToCart(productId, quantity);
    }

    @PutMapping("/item/{id}")
    public CartItem updateCartItem(@PathVariable("id") Long id,
                                   @RequestParam("quantity") @Min(1) int quantity) {
        return cartService.updateCartItem(id, quantity);
    }

    @DeleteMapping("/item/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCartItem(@PathVariable("id") Long id) {
        cartService.removeCartItem(id);
    }

}
