package com.nocontry.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_detail")
public class CartDetailEntity {

    @Id
    @SequenceGenerator(
            name = "cart_detail_id_sequence",
            sequenceName = "cart_detail_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_detail_id_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CartEntity cart;

    private Float quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true, updatable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductEntity product;

    @Transient
    private Long productId;

    private Float price;

}
