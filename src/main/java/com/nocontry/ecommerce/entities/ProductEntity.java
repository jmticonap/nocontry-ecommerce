package com.nocontry.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ProductEntity {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )
    private Long id;

    private String name;

    private String description;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ProductImagesEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<BuyEntity> buys = new ArrayList<>();

    @Column(name = "category_id")
    private Long categoryId;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "product_feature",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<FeatureEntity> features = new ArrayList<>();

}
