package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.ecommerce.entity.Category;
import com.uade.tpo.ecommerce.entity.dto.CategoryRequest;
import com.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;
import com.uade.tpo.ecommerce.exceptions.CategoryNotFoundException;

public interface ICategoryService {
  public List<Category> getAll();

  public Optional<Category> getById(Long id);

  public Category create(CategoryRequest request) throws CategoryDuplicateException;

  public Category update(CategoryRequest request) throws CategoryNotFoundException;

  public void delete(Long id) throws CategoryNotFoundException;

}
