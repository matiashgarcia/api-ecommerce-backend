package com.uade.tpo.ecommerce.controllers.admin;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.uade.tpo.ecommerce.service.admin.AdminProductService;
import com.uade.tpo.ecommerce.service.admin.IAdminProductService;

@RestController
@CrossOrigin
@RequestMapping("admin/products")
public class AdminProductController {

  @Autowired
  private IAdminProductService service;

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAll(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {

    Integer pageParsed = page == null || size == null ? 0 : page;
    Integer sizeParsed = page == null || size == null ? Integer.MAX_VALUE : size;
    Page<Product> pagedResult = service.getAll(PageRequest.of(pageParsed, sizeParsed));
    List<ProductResponse> listResult = pagedResult.getContent().stream()
        .map(a -> AdminProductService.productResponseBuilder(a))
        .toList();
    return ResponseEntity.ok(listResult);
  }

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody ProductRequest request) {
    try {
      Product result = service.create(request);
      return ResponseEntity.created(URI.create("/admin/products/" + result.getId())).body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody ProductRequest request)
      throws ProductNotFoundException {
    try {
      Product result = service.update(request);
      return ResponseEntity.created(URI.create("/admin/products/" + result.getId())).body(result);
    } catch (ProductNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) throws ProductNotFoundException {
    try {
      service.delete(id);
      return ResponseEntity.ok().build();
    } catch (ProductNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
