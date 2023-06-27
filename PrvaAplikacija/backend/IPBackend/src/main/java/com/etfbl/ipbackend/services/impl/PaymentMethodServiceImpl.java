package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.PaymentMethod;
import com.etfbl.ipbackend.models.requests.PaymentMethodRequest;
import com.etfbl.ipbackend.repositories.PaymentMethodRepository;
import com.etfbl.ipbackend.services.PaymentMethodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(paymentMethodRequest.getName());
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public Optional<PaymentMethod> getPaymentMethodById(Integer id) {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod updatePaymentMethod(Integer id, PaymentMethodRequest paymentMethodRequest) throws ExistingException {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(id);
        if (paymentMethodOptional.isEmpty())
            throw new ExistingException("Kategorija sa ID: " + id + " ne postoji u bazi podataka");

        PaymentMethod existingPaymentMethod = paymentMethodOptional.get();
        existingPaymentMethod.setName(paymentMethodRequest.getName());

        return paymentMethodRepository.save(existingPaymentMethod);
    }

    @Override
    public void deletePaymentMethod(Integer id) {
        paymentMethodRepository.deleteById(id);
    }
}
