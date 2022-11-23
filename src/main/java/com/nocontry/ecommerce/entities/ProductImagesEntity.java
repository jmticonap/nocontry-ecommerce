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
@Table(name = "product_image")
public class ProductImagesEntity {

    @Id
    @SequenceGenerator(
            name = "productimg_id_sequence",
            sequenceName = "productimg_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "productimg_id_sequence"
    )
    private Long id;
    private String path;
    @Column(name = "product_id")
    private Long productId;

}
