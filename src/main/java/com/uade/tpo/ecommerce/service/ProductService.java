package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
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
    if (categoryId == null)
      throw new NullPointerException("El id de la categoria no puede ser null");
    if (Strings.isEmpty(categoryId))
      throw new IllegalArgumentException("El id de la categoria no puede estar vacio");
    return repository.findByCategoryId(categoryId);
  }

  public List<Product> getAllBySearch(String search) {
    if (search == null)
      throw new NullPointerException("El parametro de busqueda no puede ser null");
    if (Strings.isEmpty(search))
      throw new IllegalArgumentException("El parametro de busqueda no puede estar vacio");
    if (search.length() < 3)
      throw new IllegalArgumentException("El parametro de busqueda debe tener al menos 3 caracteres");
    return repository.findBySearch(search);
  }

  public List<Product> getCartProducts(String[] productIds) {
    if (productIds.length == 0)
      throw new IllegalArgumentException("El listado de id de productos no puede estar vacio");
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
