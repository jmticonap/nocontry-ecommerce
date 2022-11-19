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
@Table(name = "userprofile")
public class UserProfileEntity {

    @Id
    @SequenceGenerator(
            name = "userprofile_id_sequence",
            sequenceName = "userprofile_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userprofile_id_sequence"
    )
    private Long id;
    private String firstname;
    private String lastname;
    @OneToOne(mappedBy = "profile")
    private AppUser user;

}
