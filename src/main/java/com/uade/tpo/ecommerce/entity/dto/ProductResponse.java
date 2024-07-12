package com.uade.tpo.ecommerce.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
  private Long id;
  private String title;
  private String description;
  private Double price;
  private Float discount;
  private Integer stock;
  private String image_url;
  private Long category_id;
  private Long user_id;

}
