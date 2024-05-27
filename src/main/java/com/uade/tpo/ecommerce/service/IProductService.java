package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.exceptions.ProductNotFoundException;

public interface IProductService {
  public Page<Product> getAll(PageRequest pageRequest);

  public List<Product> getByCategoryId(String id);

  public List<Product> getAllBySearch(String search);

  public Optional<Product> getById(Long id);

  public Product create(ProductRequest request);

  public Product update(ProductRequest request) throws ProductNotFoundException;

  public void delete(Long id) throws ProductNotFoundException;
}
