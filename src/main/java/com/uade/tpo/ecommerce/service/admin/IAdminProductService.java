package com.uade.tpo.ecommerce.service.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.exceptions.ProductNotFoundException;

public interface IAdminProductService {
  public Page<Product> getAll(PageRequest pageRequest);

  public Product create(ProductRequest request);

  public Product update(ProductRequest request) throws ProductNotFoundException;

  public void delete(Long id) throws ProductNotFoundException;
}
