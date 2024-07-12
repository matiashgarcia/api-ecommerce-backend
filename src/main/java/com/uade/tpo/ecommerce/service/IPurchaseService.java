package com.uade.tpo.ecommerce.service;

import java.util.List;

import com.uade.tpo.ecommerce.entity.dto.CartProduct;
import com.uade.tpo.ecommerce.entity.dto.PurchaseHistoryResponse;

public interface IPurchaseService {

    public void processPurchase(List<CartProduct> cartProducts) throws Exception;

    public List<PurchaseHistoryResponse> getPurchaseHistory() throws Exception;

}
