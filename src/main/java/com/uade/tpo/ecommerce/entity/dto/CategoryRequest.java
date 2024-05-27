package com.uade.tpo.ecommerce.entity.dto;

import lombok.Data;

@Data
public class CategoryRequest {
  private Long id;
  private String icon;
  private String image_url;
  private String title;
}
