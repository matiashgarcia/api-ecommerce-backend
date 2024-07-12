package com.uade.tpo.ecommerce.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uade.tpo.ecommerce.entity.Category;
import com.uade.tpo.ecommerce.entity.dto.CategoryRequest;
import com.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;
import com.uade.tpo.ecommerce.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

  @Mock
  private CategoryRepository repository;

  @InjectMocks
  private CategoryService service;

  @Test
  public void createCategory_cuandoSePasaUnaCategoriaInexistente_devuelveCreacionSatisfactoria() {
    // ARRANGE
    String tituloCategoria = "categoria1";
    List<Category> categorias = new ArrayList<Category>();
    when(repository.findByTitle(tituloCategoria)).thenReturn(categorias);

    CategoryRequest request = new CategoryRequest();
    request.setTitle(tituloCategoria);
    when(repository.save(new Category(request))).thenReturn(new Category(request));

    // ACT ASSERT
    Assertions.assertDoesNotThrow(() -> service.create(request));
  }

  @Test
  public void createCategory_cuandoSePasaUnaCategoriaExistente_devuelveCategoryDuplicateException() {
    // ARRANGE
    String tituloCategoria = "categoria1";
    List<Category> categorias = new ArrayList<Category>();
    categorias.add(new Category());
    when(repository.findByTitle(tituloCategoria)).thenReturn(categorias);

    CategoryRequest request = new CategoryRequest();
    request.setTitle(tituloCategoria);

    // ACT ASSERT
    Assertions.assertThrows(CategoryDuplicateException.class, () -> service.create(request));
  }

}
