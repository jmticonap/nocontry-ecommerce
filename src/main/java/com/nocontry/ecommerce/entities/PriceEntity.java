package com.nocontry.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

    @Positive(message = "This value must be greater than zero")
    private Float value;
    @Builder.Default
    private Boolean isActive = true;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductEntity product;

}
