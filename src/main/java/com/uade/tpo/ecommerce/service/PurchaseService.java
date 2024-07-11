package com.uade.tpo.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.CartProduct;
import com.uade.tpo.ecommerce.repository.ProductRepository;

import jakarta.transaction.Transactional;

public class PurchaseService implements IPurchaseService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void processPurchase(List<CartProduct> cartProducts) throws Exception {
        for (CartProduct item : cartProducts) {
            Product product = productRepository.findById(item.getId())
                    .orElseThrow(() -> new Exception("Product not found: " + item.getId()));

            int newStock = product.getStock() - item.getQuantity();
            if (newStock < 0) {
                throw new Exception("Stock exceeded for product: " + product.getTitle());
            }

            product.setStock(newStock);
            productRepository.save(product);
        }
    }

}
