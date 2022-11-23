package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.dto.FeatureDto;
import com.nocontry.ecommerce.entities.FeatureEntity;
import com.nocontry.ecommerce.repositories.FeatureRepository;
import com.nocontry.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final ProductRepository productRepository;

    public List<FeatureEntity> findAll() throws Exception {
        Optional<List<FeatureEntity>> features = Optional.of(featureRepository.findAll());

        return features.orElseThrow(() -> new Exception("Is some problem with fetching the list"));
    }

    public FeatureEntity save(FeatureEntity feature) throws Exception {
        try {
            return featureRepository.save(feature);
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }
    }

    public FeatureEntity save(FeatureDto featureDto) throws Exception {
        FeatureEntity feature = FeatureEntity.builder()
                .name(featureDto.getName())
                .build();
        return save(feature);
    }

    public void delete(FeatureEntity feature) throws Exception {
        try {
            featureRepository.delete(feature);
        } catch (Exception err) {
            log.error(err.getMessage());
            throw new Exception(err.getMessage());
        }
    }

}
