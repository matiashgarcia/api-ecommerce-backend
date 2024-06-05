package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductResponse;
import com.uade.tpo.ecommerce.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

  @Autowired
  private ProductRepository repository;

  public Optional<Product> getById(Long id) {
    return repository.findById(id);
  }

  public List<Product> getByCategoryId(String categoryId) {
    return repository.findByCategoryId(categoryId);
  }

  public List<Product> getAllBySearch(String search) {
    return repository.findBySearch(search);
  }

  public List<Product> getCartProducts(String[] productIds) {
    return repository.findCartProductsByIds(productIds);
  }

  public static ProductResponse productResponseBuilder(Product p) {
    return ProductResponse.builder()
        .id(p.getId())
        .title(p.getTitle())
        .description(p.getDescription())
        .price(p.getPrice())
        .discount(p.getDiscount())
        .stock(p.getStock())
        .image_url(p.getImage_url())
        .category_id(p.getCategory_id())
        .user_id(p.getUser_id())
        .build();
  }

}
