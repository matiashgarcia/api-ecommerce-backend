package com.uade.tpo.ecommerce.entity.dto;

import com.uade.tpo.ecommerce.entity.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
  private Long id;
  private String avatar_img;
  private String firstname;
  private String lastname;
  private String email;
  private Role role;
}
