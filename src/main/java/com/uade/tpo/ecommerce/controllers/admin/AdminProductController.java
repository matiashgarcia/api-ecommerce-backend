package com.uade.tpo.ecommerce.controllers.admin;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.entity.dto.ProductResponse;
import com.uade.tpo.ecommerce.exceptions.ProductNotFoundException;
import com.uade.tpo.ecommerce.service.IProductService;
import com.uade.tpo.ecommerce.service.ProductService;

@RestController
@RequestMapping("admin/products")
public class AdminProductController {

  @Autowired
  private IProductService service;

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAll(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {

    final Integer pageParsed = page == null || size == null ? 0 : page;
    final Integer sizeParsed = page == null || size == null ? Integer.MAX_VALUE : size;
    final Page<Product> pagedResult = service.getAll(PageRequest.of(pageParsed, sizeParsed));

    final List<ProductResponse> listResult = pagedResult.getContent().stream()
        .map(a -> ProductService.productResponseBuilder(a))
        .toList();
    return ResponseEntity.ok(listResult);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
    Optional<Product> result = service.getById(id);
    if (result.isPresent())
      return ResponseEntity.ok(ProductService.productResponseBuilder(result.get()));
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody ProductRequest request) {
    Product result = service.create(request);
    return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody ProductRequest request)
      throws ProductNotFoundException {
    Product result = service.update(request);
    return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) throws ProductNotFoundException {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
