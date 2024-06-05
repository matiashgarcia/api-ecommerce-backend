package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.ecommerce.entity.Product;

public interface IProductService {
  public Optional<Product> getById(Long id);

  public List<Product> getByCategoryId(String id);

  public List<Product> getAllBySearch(String search);

  public List<Product> getCartProducts(String[] productIds);
}
