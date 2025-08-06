package com.example.shop_api.controller;

import com.example.shop_api.model.CartItem;
import com.example.shop_api.model.Product;
import com.example.shop_api.service.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    void testAddToCart() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        when(cartService.addToCart(eq(1L), eq(2))).thenReturn(cartItem);

        mockMvc.perform(post("/api/cart/add")
                        .param("productId", "1")
                        .param("quantity", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.product.name").value("Test Product"));
    }


    @Test
    void testUpdateCartItem() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(5);

        when(cartService.updateCartItem(eq(1L), eq(5))).thenReturn(cartItem);

        mockMvc.perform(put("/api/cart/item/1")
                        .param("quantity", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    void testRemoveCartItem() throws Exception {
        doNothing().when(cartService).removeCartItem(1L);

        mockMvc.perform(delete("/api/cart/item/1"))
                .andExpect(status().isNoContent());
    }
}
