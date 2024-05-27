package com.uade.tpo.ecommerce.entity;

import com.uade.tpo.ecommerce.entity.dto.ProductRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {

  public Product() {
  }

  public Product(ProductRequest request) {
    this.id = request.getId();
    this.title = request.getTitle();
    this.description = request.getDescription();
    this.price = request.getPrice();
    this.discount = request.getDiscount();
    this.stock = request.getStock();
    this.image_url = request.getImage_url();
    this.category_id = request.getCategory_id();
    this.user_id = request.getUser_id();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String title;

  @Lob
  @Column(length = 65000)
  private String description;

  @Column
  private Double price;

  @Column
  private Float discount;

  @Column
  private String stock;

  @Column
  private String image_url;

  // https://stackoverflow.com/questions/27930449/jpa-many-to-one-relation-need-to-save-only-id
  @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  private Category category;

  @Column(name = "category_id", nullable = false)
  private Long category_id;

  @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @Column(name = "user_id", nullable = false)
  private Long user_id;

}
