package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Purchase;
import com.etfbl.ipbackend.models.requests.PurchaseRequest;
import com.etfbl.ipbackend.services.PurchaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private static final Logger logger = LogManager.getLogger(PurchaseController.class);

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequest purchaseRequest) {
        Purchase purchase = purchaseService.createPurchase(purchaseRequest);
        logger.info("POST created purchase");
        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable("id") Integer id) {
        Optional<Purchase> purchaseOptional = purchaseService.getPurchaseById(id);
        return purchaseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/purchase-user/{userId}")
    public ResponseEntity<List<Purchase>> getAllPurchaseByUserId(@PathVariable("userId") Integer id) {
        List<Purchase> purchases = purchaseService.getPurchaseByUserId(id);
        logger.info("GET purchase by user ID: " + id);
        return ResponseEntity.ok(purchases);
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getPurchases();

        return ResponseEntity.ok(purchases);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") Integer id, @RequestBody PurchaseRequest purchaseRequest) {
        try {
            Purchase purchase = purchaseService.updatePurchase(id, purchaseRequest);

            return ResponseEntity.ok(purchase);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable("id") Integer id) {
        purchaseService.deletePurchase(id);

        return ResponseEntity.noContent().build();
    }
}
