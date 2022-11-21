package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.dto.FeatureDto;
import com.nocontry.ecommerce.entities.FeatureEntity;
import com.nocontry.ecommerce.repositories.FeatureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeatureService {
    private final FeatureRepository featureRepository;
    public FeatureEntity save(FeatureEntity feature) {
        return featureRepository.save(feature);
    }

    public FeatureEntity save(FeatureDto featureDto) {
        return save(FeatureEntity.builder()
                .name(featureDto.getName())
                .products(featureDto.getProducts())
                .build());
    }

    Optional<FeatureEntity> findById(Long id){
        return this.featureRepository.findById(id);
    }

    public List<FeatureEntity> findAll() {
        return featureRepository.findAll();
    }

    public FeatureEntity update(Long id,FeatureDto featureDto) throws Exception {
        Optional<FeatureEntity> feature=this.featureRepository.findById(id);
        if (feature.isEmpty()){
            throw new Exception("Id feature no valido");
        }
        feature.get().setName(featureDto.getName());
        feature.get().setProducts(featureDto.getProducts());
        return featureRepository.save(feature.get());
    }
    public void delete(Long featureId) {
        Optional<FeatureEntity> entity= featureRepository.findById(featureId);
        try {
            featureRepository.delete(entity.get());
        } catch (Exception err) {
            log.error(err.getMessage());
        }
    }
}
