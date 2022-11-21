package com.nocontry.ecommerce.controllers;

import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.services.ProductService;
import com.nocontry.ecommerce.dto.ProductTdo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> save(@RequestBody ProductTdo productTdo){
        return new ResponseEntity<ProductEntity>(productService.save(productTdo), HttpStatus.OK);
    }

}
