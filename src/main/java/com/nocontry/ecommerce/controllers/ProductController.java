package com.nocontry.ecommerce.controllers;

import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable(name = "id") Long id) {
        try {
            return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

/*    @PostMapping
    public ResponseEntity<ProductEntity> save(@RequestBody ProductTdo productTdo) throws Exception {
        return new ResponseEntity<>(productService.save(productTdo), HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<ProductEntity> save(@RequestBody ProductEntity product) throws Exception {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }


    @PostMapping("/{productId}/add_images")
    public ResponseEntity<ProductEntity> addImages(
            @PathVariable(name = "productId") Long productId,
            @RequestBody List<Long> imagesIds) throws Exception {
        try {
            ProductEntity product = productService.addImages(productId, imagesIds);
            ResponseEntity<ProductEntity> res = new ResponseEntity<>(product, HttpStatus.OK);
            return res;
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("/{productId}/add_features")
    public ResponseEntity<ProductEntity> addFeatures(
            @PathVariable(name = "productId") Long productId,
            @RequestBody List<Long> featuresIds) throws Exception {
        try {
            ProductEntity product = productService.addFeatures(productId, featuresIds);
            ResponseEntity<ProductEntity> res = new ResponseEntity<>(product, HttpStatus.OK);
            return res;
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
