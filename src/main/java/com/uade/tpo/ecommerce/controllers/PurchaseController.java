package com.uade.tpo.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.dto.CartProduct;
import com.uade.tpo.ecommerce.entity.dto.PurchaseHistoryResponse;
import com.uade.tpo.ecommerce.service.IPurchaseService;

@RestController
@CrossOrigin
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @PostMapping()
    public ResponseEntity<String> handlePurchase(@RequestBody List<CartProduct> cartProducts) {
        try {
            purchaseService.processPurchase(cartProducts);
            return ResponseEntity.ok("Purchase successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<PurchaseHistoryResponse>> getPurchaseHistory(@PathVariable Long userId) {
        try {
            List<PurchaseHistoryResponse> purchaseHistory = purchaseService.getPurchaseHistory(userId);
            return ResponseEntity.ok(purchaseHistory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
