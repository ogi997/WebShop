package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Purchase;
import com.etfbl.ipbackend.models.requests.PurchaseRequest;
import com.etfbl.ipbackend.repositories.PurchaseRepository;
import com.etfbl.ipbackend.services.PurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase createPurchase(PurchaseRequest purchaseRequest) {
        Purchase purchase = new Purchase();
        purchase.setFkPayment(purchaseRequest.getFkPayment());
        purchase.setFkProductPurchase(purchaseRequest.getFkProductPurchase());
        purchase.setFkUse(purchaseRequest.getFkUse());
        purchase.setCardNumber(purchaseRequest.getCardNumber());
        return purchaseRepository.save(purchase);
    }

    @Override
    public Optional<Purchase> getPurchaseById(Integer id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> getPurchaseByUserId(Integer id) {
        return purchaseRepository.getPurchaseByFkUse(id);
    }

    @Override
    public Purchase updatePurchase(Integer id, PurchaseRequest purchaseRequest) throws ExistingException {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(id);

        if (purchaseOptional.isEmpty())
            throw new ExistingException("Purchase sa ID: " + id + " ne postoji u bazi pdoataka");

        Purchase purchase = purchaseOptional.get();
        purchase.setFkPayment(purchaseRequest.getFkPayment());
        purchase.setFkProductPurchase(purchaseRequest.getFkProductPurchase());
        purchase.setFkUse(purchaseRequest.getFkUse());
        purchase.setCardNumber(purchaseRequest.getCardNumber());
        return purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchase(Integer id) {
        purchaseRepository.deleteById(id);
    }
}
