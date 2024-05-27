package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.ecommerce.entity.Category;
import com.uade.tpo.ecommerce.entity.dto.CategoryRequest;
import com.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;
import com.uade.tpo.ecommerce.exceptions.CategoryNotFoundException;
import com.uade.tpo.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {

  @Autowired
  private CategoryRepository repository;

  public List<Category> getAll() {
    return repository.findAll();
  }

  public Optional<Category> getById(Long id) {
    return repository.findById(id);
  }

  @Transactional(rollbackFor = Throwable.class)
  public Category create(CategoryRequest request) throws CategoryDuplicateException {
    List<Category> categories = repository.findByTitle(request.getTitle());
    if (categories.isEmpty())
      return repository.save(new Category(request));
    throw new CategoryDuplicateException();
  }

  public Category update(CategoryRequest request) throws CategoryNotFoundException {
    Optional<Category> category = repository.findById(request.getId());
    if (category.isPresent())
      return repository.save(new Category(request));
    throw new CategoryNotFoundException();
  }

  public void delete(Long id) throws CategoryNotFoundException {
    Optional<Category> category = repository.findById(id);
    if (category.isEmpty())
      throw new CategoryNotFoundException();
    repository.deleteById(id);
  }

}
