package com.uade.tpo.ecommerce.service;

import java.util.List;

import com.uade.tpo.ecommerce.entity.dto.CartProduct;

public interface IPurchaseService {

    void processPurchase(List<CartProduct> cartProducts) throws Exception;

}
