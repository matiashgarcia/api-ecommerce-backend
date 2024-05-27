package com.uade.tpo.ecommerce.entity;

import com.uade.tpo.ecommerce.entity.dto.CategoryRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Category {

  public Category() {
  }

  public Category(CategoryRequest categoryRequest) {
    this.id = categoryRequest.getId();
    this.icon = categoryRequest.getIcon();
    this.image_url = categoryRequest.getImage_url();
    this.title = categoryRequest.getTitle();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String title;

  @Column
  private String image_url;

  @Column
  private String icon;

  // @OneToMany(mappedBy = "categories")
  // private List<Product> products;
}
