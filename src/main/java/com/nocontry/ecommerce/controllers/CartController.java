package com.nocontry.ecommerce.controllers;

import com.nocontry.ecommerce.entities.CartDetailEntity;
import com.nocontry.ecommerce.entities.CartEntity;
import com.nocontry.ecommerce.security.TokenUtils;
import com.nocontry.ecommerce.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
@CrossOrigin
public class CartController {

    @Autowired
    private final CartService cartService;

    @GetMapping("/active")
    public ResponseEntity<?> getAvtiveCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws Exception {
        String userEmail = TokenUtils
                .getAuthentication(
                        token.replace("Bearer ", "")
                )
                .getPrincipal().toString();
        Optional<CartEntity> activeCart = cartService.getActive(userEmail);

        if (activeCart.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(activeCart);
    }

    @PatchMapping("/add_products")
    public ResponseEntity<?> addProducts(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody List<CartDetailEntity> products) throws Exception {
        String userEmail = TokenUtils
                .getAuthentication(
                        token.replace("Bearer ", "")
                )
                .getPrincipal().toString();
        products = cartService.addProducts(userEmail, products);

        if(products == null || products.size() < 1)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(products);
    }

}
