package com.uade.tpo.ecommerce.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Category;
import com.uade.tpo.ecommerce.service.ICategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {

  @Autowired
  private ICategoryService service;

  @GetMapping
  public ResponseEntity<List<Category>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

}
