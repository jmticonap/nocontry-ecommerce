package com.nocontry.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileDto {

    private String firstname;
    private String lastname;
    private Long userId;

}
