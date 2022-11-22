package com.nocontry.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "category")
public class CategoryEntity {

    @Id
    @SequenceGenerator(
            name = "category_id_sequence",
            sequenceName = "category_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_id_sequence"
    )
    private Long id;
    private String name;
    /*@ManyToOne(optional = true)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    @JsonBackReference
    private CategoryEntity parent;*/

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<ProductEntity> products;*/

}
