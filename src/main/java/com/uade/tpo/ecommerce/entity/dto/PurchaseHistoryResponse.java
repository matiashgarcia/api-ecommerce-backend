package com.uade.tpo.ecommerce.entity.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseHistoryResponse {

    private Long purchaseId;
    private LocalDateTime date;
    private Double totalAmount;
    private List<PurchaseItemResponse> items;

    @Data
    @Builder
    public static class PurchaseItemResponse {
        private Long productId;
        private String productName;
        private int quantity;
        private Double price;
        private Double totalCost;

    }

}
