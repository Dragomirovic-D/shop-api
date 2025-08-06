package com.example.shop_api.service;

import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.model.CartItem;
import com.example.shop_api.model.Product;
import com.example.shop_api.repository.CartRepository;
import com.example.shop_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItem addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartRepository.save(cartItem);
    }

    public List<CartItem> getCart() {
        return cartRepository.findAll();
    }

    public CartItem updateCartItem(Long id, int quantity) {
        CartItem item = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found with id " + id));

        item.setQuantity(quantity);
        return cartRepository.save(item);
    }

    public void removeCartItem(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new IllegalArgumentException("Cart item not found with id " + id);
        }
        cartRepository.deleteById(id);
    }
}
