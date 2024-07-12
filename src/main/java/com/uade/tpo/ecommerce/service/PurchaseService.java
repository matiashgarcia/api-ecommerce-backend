package com.uade.tpo.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.Purchase;
import com.uade.tpo.ecommerce.entity.PurchaseItem;
import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.CartProduct;
import com.uade.tpo.ecommerce.entity.dto.PurchaseHistoryResponse;
import com.uade.tpo.ecommerce.entity.dto.PurchaseHistoryResponse.PurchaseItemResponse;
import com.uade.tpo.ecommerce.exceptions.PurchaseException;
import com.uade.tpo.ecommerce.repository.ProductRepository;
import com.uade.tpo.ecommerce.repository.PurchaseRepository;
import com.uade.tpo.ecommerce.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void processPurchase(List<CartProduct> cartProducts) throws Exception {
        Long userId = authService.getLoggedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PurchaseException("User not found: " + userId));

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setDate(LocalDateTime.now());

        List<PurchaseItem> purchaseItems = new ArrayList<>();
        Double totalAmount = 0.0;

        for (CartProduct item : cartProducts) {
            Product product = productRepository.findById(item.getId())
                    .orElseThrow(() -> new PurchaseException("Product not found: " + item.getId()));

            int newStock = product.getStock() - item.getQuantity();
            if (newStock < 0) {
                throw new PurchaseException("Stock exceeded for product: " + product.getTitle());
            }

            // Calculate the effective price after discount
            Double unit_price = product.getPrice() - (product.getPrice() * product.getDiscount() / 100);
            Double totalCost = unit_price * item.getQuantity();

            product.setStock(newStock);
            productRepository.save(product);

            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setPurchase(purchase);
            purchaseItem.setProduct(product);
            purchaseItem.setQuantity(item.getQuantity());
            purchaseItem.setPrice(unit_price);
            purchaseItem.setTotalCost(totalCost);

            purchaseItems.add(purchaseItem);
            totalAmount = totalAmount + totalCost;
        }

        purchase.setItems(purchaseItems);
        purchase.setTotalAmount(totalAmount);
        purchaseRepository.save(purchase);
    }

    public List<PurchaseHistoryResponse> getPurchaseHistory() throws Exception {
        Long userId = authService.getLoggedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: " + userId));

        List<Purchase> purchases = purchaseRepository.findByUserId(userId);

        return purchases.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PurchaseHistoryResponse convertToDTO(Purchase purchase) {
        List<PurchaseItemResponse> items = purchase.getItems().stream().map(item -> PurchaseItemResponse.builder()
                .productId(item.getProduct().getId())
                .productName(item.getProduct().getTitle())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .totalCost(item.getTotalCost())
                .build()).collect(Collectors.toList());

        return PurchaseHistoryResponse.builder()
                .purchaseId(purchase.getId())
                .date(purchase.getDate())
                .totalAmount(purchase.getTotalAmount())
                .items(items)
                .build();
    }
}
