package com.uade.tpo.ecommerce.entity.dto;

import lombok.Data;

@Data
public class UserRequest {
  private Long id;
  private String avatar_img;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Long role_id;
}
