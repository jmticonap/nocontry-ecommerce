package com.nocontry.ecommerce.controllers;

import com.nocontry.ecommerce.dto.ProductDto;
import com.nocontry.ecommerce.entities.BuyEntity;
import com.nocontry.ecommerce.entities.CategoryEntity;
import com.nocontry.ecommerce.entities.ProductEntity;
import com.nocontry.ecommerce.marshelling.ProductMarsheller;
import com.nocontry.ecommerce.services.BuyService;
import com.nocontry.ecommerce.services.CategoryService;
import com.nocontry.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final BuyService buyService;

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProductEntity>>> findAll(
            Pageable pageable,
            PagedResourcesAssembler<ProductEntity> assembler) {
        Page<ProductEntity> products = productService.findAll(pageable);
        return ResponseEntity.ok().body(assembler.toModel(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) throws Exception {
        Optional<ProductEntity> product = productService.findById(id);

        if (product.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(product.get());
    }

    @GetMapping("/findbycategory/{categoryId}")
    public ResponseEntity<?> findByCategory(@RequestParam(name = "page", required = false) Integer page,
                                            @RequestParam(name = "size", required = false) Integer size,
                                            @PathVariable(name = "categoryId") Long categoryId) {
        Optional<CategoryEntity> category = categoryService.findById(categoryId);

        if (category.isEmpty())
            return ResponseEntity.notFound().build();

        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 20);

        return ResponseEntity.ok().body(productService.findByCategory(category.get(), pageable));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductEntity product) throws Exception {
        Optional<ProductEntity> newProduct = null;

        try {
            newProduct = productService.save(product);
        } catch (Exception err) {
            return ResponseEntity.unprocessableEntity().body(err.getMessage());
        }

        URI uri = getUri(newProduct.get().getId());

        return ResponseEntity.created(uri).body(newProduct.get());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> setCategory(
            @PathVariable(name = "id") Long id,
            @RequestBody Map<String, Long> categoryId) throws Exception {
        Optional<CategoryEntity> category = categoryService
                .findById(categoryId.get("categoryId"));
        Optional<ProductEntity> product = productService.findById(id);

        if (category.isEmpty())
            return ResponseEntity.unprocessableEntity().build();

        product.get().setCategory(category.get());
        Optional<ProductEntity> newProduct = productService.save(product.get());

        URI uri = getUri(newProduct.get().getId());
        return ResponseEntity.created(uri).body(newProduct.get());
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


    @PatchMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody List<Map<String, Number>> data){
        List<BuyEntity> buys = buyService.save(data);

        if(buys == null || buys.size() < 1)
            return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok(buys);
    }

}
