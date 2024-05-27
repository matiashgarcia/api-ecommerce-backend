package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.entity.dto.ProductResponse;
import com.uade.tpo.ecommerce.exceptions.ProductNotFoundException;
import com.uade.tpo.ecommerce.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

  @Autowired
  private ProductRepository repository;

  public Page<Product> getAll(PageRequest pageable) {
    return repository.findAll(pageable);
  }

  public List<Product> getByCategoryId(String id) {
    return repository.findByCategoryId(id);
  }

  public List<Product> getAllBySearch(String search) {
    return repository.findBySearch(search);
  }

  public Optional<Product> getById(Long id) {
    return repository.findById(id);
  }

  public Product create(ProductRequest request) {
    return repository.save(new Product(request));
  }

  public Product update(ProductRequest request) throws ProductNotFoundException {
    Optional<Product> product = repository.findById(request.getId());
    if (product.isPresent())
      return repository.save(new Product(request));
    throw new ProductNotFoundException();
  }

  public void delete(Long id) throws ProductNotFoundException {
    Optional<Product> product = repository.findById(id);
    if (product.isEmpty())
      throw new ProductNotFoundException();
    repository.deleteById(id);
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
