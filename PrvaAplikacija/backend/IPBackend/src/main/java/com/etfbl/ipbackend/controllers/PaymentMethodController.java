package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.PaymentMethod;
import com.etfbl.ipbackend.models.requests.PaymentMethodRequest;
import com.etfbl.ipbackend.services.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethodRequest paymentMethodRequest) {
        PaymentMethod paymentMethod = paymentMethodService.createPaymentMethod(paymentMethodRequest);

        return ResponseEntity.ok(paymentMethod);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable("id") Integer id) {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.getPaymentMethodById(id);
        return paymentMethodOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethods();

        return ResponseEntity.ok(paymentMethods);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable("id") Integer id, @RequestBody PaymentMethodRequest paymentMethodRequest) {
        try {
            PaymentMethod paymentMethod = paymentMethodService.updatePaymentMethod(id, paymentMethodRequest);

            return ResponseEntity.ok(paymentMethod);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable("id") Integer id) {
        paymentMethodService.deletePaymentMethod(id);

        return ResponseEntity.noContent().build();
    }
}
