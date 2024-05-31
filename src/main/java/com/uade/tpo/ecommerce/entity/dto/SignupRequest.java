package com.uade.tpo.ecommerce.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Long role_id;

}
