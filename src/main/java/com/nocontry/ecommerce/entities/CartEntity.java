package com.nocontry.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @SequenceGenerator(
            name = "cart_id_sequence",
            sequenceName = "cart_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_id_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AppUser user;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Builder.Default
    private Boolean isPurchased = false;

    @Transient
    @Builder.Default
    private Float total = 0F;

    @OneToOne(mappedBy = "cart")
    private DeliverDataEntity deliverData;

    @OneToOne(mappedBy = "cart")
    private PurchaseEntity purchase;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.ALL})
    private List<CartDetailEntity> products = new ArrayList<>();
    
}
