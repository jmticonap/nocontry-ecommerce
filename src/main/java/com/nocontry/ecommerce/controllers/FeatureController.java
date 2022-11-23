package com.nocontry.ecommerce.controllers;

import com.nocontry.ecommerce.dto.FeatureDto;
import com.nocontry.ecommerce.entities.FeatureEntity;
import com.nocontry.ecommerce.services.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feature")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping
    public ResponseEntity<List<FeatureEntity>> findAll() throws Exception {
        try {
            return new ResponseEntity<>(featureService.findAll(), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping
    public ResponseEntity<FeatureEntity> save(FeatureDto featureDto) throws Exception {
        try {
            FeatureEntity feature = featureService.save(featureDto);
            return new ResponseEntity<>(feature, HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }

    }

}
