package com.uade.tpo.ecommerce.service.admin;

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
import com.uade.tpo.ecommerce.service.AuthService;

@Service
public class AdminProductService implements IAdminProductService {

  @Autowired
  private ProductRepository repository;
  @Autowired
  private AuthService authService;

  public Page<Product> getAll(PageRequest pageable) {
    Long userId = authService.getLoggedUserId();
    Page<Product> pagedResult = repository.findAllWithPageable(userId, pageable);
    return pagedResult;
  }

  public Product create(ProductRequest request) {
    Long userId = authService.getLoggedUserId();
    ProductRequest product = request;
    product.setUser_id(userId);
    return repository.save(new Product(product));
  }

  public Product update(ProductRequest request) throws ProductNotFoundException {
    Optional<Product> prod = repository.findById(request.getId());
    Long userId = authService.getLoggedUserId();
    if (prod.isPresent() && prod.get().getUser_id() == userId) {
      ProductRequest product = request;
      product.setUser_id(userId);
      return repository.save(new Product(product));
    }
    throw new ProductNotFoundException();
  }

  public void delete(Long id) throws ProductNotFoundException {
    Optional<Product> prod = repository.findById(id);
    Long userId = authService.getLoggedUserId();
    if (prod.isPresent() && prod.get().getUser_id() == userId) {
      repository.deleteById(id);
    } else {
      throw new ProductNotFoundException();
    }
  }

  public static ProductResponse productResponseBuilder(Product p) {
    return ProductResponse.builder()
        .id(p.getId())
        .title(p.getTitle())
        .price(p.getPrice())
        .discount(p.getDiscount())
        .stock(p.getStock())
        .image_url(p.getImage_url())
        .category_id(p.getCategory_id())
        .user_id(p.getUser_id())
        .build();
  }

}
