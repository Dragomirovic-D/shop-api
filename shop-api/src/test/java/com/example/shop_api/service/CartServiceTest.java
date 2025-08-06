package com.example.shop_api.service;

import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.model.CartItem;
import com.example.shop_api.model.Product;
import com.example.shop_api.repository.CartRepository;
import com.example.shop_api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    private Product product;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(10.0);

        cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);
    }

    @Test
    void testAddToCart_ProductExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(cartRepository.save(any(CartItem.class))).thenReturn(cartItem);

        CartItem result = cartService.addToCart(1L, 2);

        assertNotNull(result);
        assertEquals(2, result.getQuantity());
        assertEquals("Test Product", result.getProduct().getName());

        verify(productRepository, times(1)).findById(1L);
        verify(cartRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void testAddToCart_ProductNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> cartService.addToCart(99L, 1));

        verify(productRepository, times(1)).findById(99L);
        verifyNoInteractions(cartRepository);
    }

    @Test
    void testGetCart() {
        when(cartRepository.findAll()).thenReturn(Arrays.asList(cartItem));

        List<CartItem> items = cartService.getCart();

        assertEquals(1, items.size());
        assertEquals("Test Product", items.get(0).getProduct().getName());

        verify(cartRepository, times(1)).findAll();
    }

    @Test
    void testUpdateCartItem_ItemExists() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cartItem));
        when(cartRepository.save(any(CartItem.class))).thenReturn(cartItem);

        CartItem updated = cartService.updateCartItem(1L, 5);

        assertEquals(5, updated.getQuantity());
        verify(cartRepository, times(1)).save(cartItem);
    }

    @Test
    void testUpdateCartItem_ItemNotFound() {
        when(cartRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> cartService.updateCartItem(99L, 5));

        verify(cartRepository, times(1)).findById(99L);
    }

    @Test
    void testRemoveCartItem_ItemExists() {
        when(cartRepository.existsById(1L)).thenReturn(true);

        cartService.removeCartItem(1L);

        verify(cartRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRemoveCartItem_ItemNotFound() {
        when(cartRepository.existsById(99L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> cartService.removeCartItem(99L));

        verify(cartRepository, never()).deleteById(anyLong());
    }
}
