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

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @Mock
  private ProductRepository repository;

  @InjectMocks
  private ProductService service;

  @Test
  public void getByCategoryId_cuandoRecibeNullComoCategoryId_arrojaNullPointerException() {
    // Arrange
    String categoryId = null;

    // Act
    NullPointerException response = Assertions.assertThrows(NullPointerException.class,
        () -> service.getByCategoryId(categoryId));

    // Assert
    String expectedMessage = "El id de la categoria no puede ser null";
    Assertions.assertTrue(response.getMessage().contains(expectedMessage));
  }

  @Test
  public void getByCategoryId_cuandoRecibeStringVacioComoCategoryId_arrojaIllegalArgumentException() {
    // Arrange
    String categoryId = "";

    // Act
    IllegalArgumentException response = Assertions.assertThrows(IllegalArgumentException.class,
        () -> service.getByCategoryId(categoryId));

    // Assert
    String expectedMessage = "El id de la categoria no puede estar vacio";
    Assertions.assertTrue(response.getMessage().contains(expectedMessage));
  }

  @Test
  public void getAllBySearch_cuandoRecibeNullComoSearch_arrojaNullPointerException() {
    // Arrange
    String search = null;

    // Act
    NullPointerException response = Assertions.assertThrows(NullPointerException.class,
        () -> service.getAllBySearch(search));

    // Assert
    String expectedMessage = "El parametro de busqueda no puede ser null";
    Assertions.assertTrue(response.getMessage().contains(expectedMessage));
  }

  @Test
  public void getAllBySearch_cuandoRecibeStringVacioComoSearch_arrojaIllegalArgumentException() {
    // Arrange
    String search = "";

    // Act
    IllegalArgumentException response = Assertions.assertThrows(IllegalArgumentException.class,
        () -> service.getAllBySearch(search));

    // Assert
    String expectedMessage = "El parametro de busqueda no puede estar vacio";
    Assertions.assertTrue(response.getMessage().contains(expectedMessage));
  }

  @Test
  public void getAllBySearch_cuandoRecibeStringMuyCortoComoSearch_arrojaIllegalArgumentException() {
    // Arrange
    String search1 = "a";
    String search2 = "ab";

    // Act
    IllegalArgumentException response1 = Assertions.assertThrows(IllegalArgumentException.class,
        () -> service.getAllBySearch(search1));
    IllegalArgumentException response2 = Assertions.assertThrows(IllegalArgumentException.class,
        () -> service.getAllBySearch(search2));

    // Assert
    String expectedMessage = "El parametro de busqueda debe tener al menos 3 caracteres";
    Assertions.assertTrue(response1.getMessage().contains(expectedMessage));
    Assertions.assertTrue(response2.getMessage().contains(expectedMessage));
  }

  @Test
  public void getAllBySearch_cuandoSePasaUnParametroSearchCorrecto_devuelveUnListadoDeProductosMatcheados() {
    // Arrange
    String search = "abc";
    List<Product> productos = new ArrayList<Product>();
    productos.add(new Product());
    when(repository.findBySearch(search)).thenReturn(productos);

    // Act
    List<Product> response = Assertions.assertDoesNotThrow(() -> service.getAllBySearch(search));

    // Assert
    Assertions.assertTrue(response.size() > 0);
  }

  @Test
  public void getCartProducts_cuandoRecibeUnaListaVaciaDeIdsDeProductos_arrojaIllegalArgumentException() {
    // Arrange
    String[] productIds = new String[0];

    // Act
    IllegalArgumentException response = Assertions.assertThrows(IllegalArgumentException.class,
        () -> service.getCartProducts(productIds));

    // Assert
    String expectedMessage = "El listado de id de productos no puede estar vacio";
    Assertions.assertTrue(response.getMessage().contains(expectedMessage));
  }
}
