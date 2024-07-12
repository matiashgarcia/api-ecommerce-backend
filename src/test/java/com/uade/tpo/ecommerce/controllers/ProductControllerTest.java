package com.uade.tpo.ecommerce.controllers;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductResponse;
import com.uade.tpo.ecommerce.service.IProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

  @Mock
  private IProductService service;

  @InjectMocks
  private ProductController controller;

  @Test
  public void getProductById_cuandoSeLePasaUnIdValido_devuelveDatosDeProducto() {
    // ARRANGE
    Long productId = Long.valueOf(1);
    String productTitle = "Product1";

    Product product = new Product();
    product.setId(productId);
    product.setTitle(productTitle);
    Optional<Product> OptionalProduct = Optional.of(product);

    when(service.getById(productId)).thenReturn(OptionalProduct);

    // ACT ASSERT
    ResponseEntity<ProductResponse> response = Assertions.assertDoesNotThrow(() -> controller.getById(productId));
    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    Assertions.assertTrue(response.hasBody());
    Assertions.assertTrue(response.getBody().getId() == productId);
    Assertions.assertTrue(response.getBody().getTitle().equals(productTitle));
  }

  @Test
  public void getProductById_cuandoSeLePasaUnIdInvalido_devuelveSinContenido() {
    // ARRANGE
    Long productId = Long.valueOf(-1);
    when(service.getById(productId)).thenReturn(Optional.empty());

    // ACT ASSERT
    ResponseEntity<ProductResponse> response = Assertions.assertDoesNotThrow(() -> controller.getById(productId));
    Assertions.assertFalse(response.hasBody());
  }

}
