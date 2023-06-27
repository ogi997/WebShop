package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Purchase;
import com.etfbl.ipbackend.models.requests.PurchaseRequest;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    Purchase createPurchase(PurchaseRequest purchaseRequest);

    Optional<Purchase> getPurchaseById(Integer id);

    List<Purchase> getPurchases();

    List<Purchase> getPurchaseByUserId(Integer id);

    Purchase updatePurchase(Integer id, PurchaseRequest purchaseRequest) throws ExistingException;

    void deletePurchase(Integer id);
}
