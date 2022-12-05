package com.nocontry.ecommerce.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductEntity implements Serializable {

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

    @Builder.Default
    private Double stock = 0.0;

    @Transient
    @Builder.Default
    private Float price = 0F;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    private List<ProductImagesEntity> images = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BuyEntity> buys = new ArrayList<>();

    @ManyToOne
    //@JoinColumn(name = "category_id", updatable = true, nullable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CategoryEntity category;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "product_feature",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<FeatureEntity> features = new ArrayList<>();

}
