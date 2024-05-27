package com.uade.tpo.ecommerce.controllers.admin;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Category;
import com.uade.tpo.ecommerce.entity.dto.CategoryRequest;
import com.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;
import com.uade.tpo.ecommerce.exceptions.CategoryNotFoundException;
import com.uade.tpo.ecommerce.service.ICategoryService;

@RestController
@RequestMapping("admin/categories")
public class AdminCategoryController {

  @Autowired
  private ICategoryService service;

  @GetMapping
  public ResponseEntity<List<Category>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getById(@PathVariable Long id) {
    Optional<Category> result = service.getById(id);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody CategoryRequest request)
      throws CategoryDuplicateException {
    Category result = service.create(request);
    return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody CategoryRequest request)
      throws CategoryNotFoundException {
    Category result = service.update(request);
    return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id)
      throws CategoryNotFoundException {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
