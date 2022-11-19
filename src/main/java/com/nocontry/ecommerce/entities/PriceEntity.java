package com.nocontry.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price")
public class PriceEntity {

    @Id
    @SequenceGenerator(
            name = "price_id_sequence",
            sequenceName = "price_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "price_id_sequence"
    )
    private Long id;
    private Double value;
    private Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private ProductEntity product;

}
