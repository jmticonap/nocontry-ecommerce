package com.nocontry.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feature")
public class FeatureEntity {

    @Id
    @SequenceGenerator(
            name = "feature_id_sequence",
            sequenceName = "feature_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "feature_id_sequence"
    )
    private Long id;
    private String name;


}
