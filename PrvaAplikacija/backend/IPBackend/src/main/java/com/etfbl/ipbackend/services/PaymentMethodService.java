package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.PaymentMethod;
import com.etfbl.ipbackend.models.requests.PaymentMethodRequest;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodService {
    PaymentMethod createPaymentMethod(PaymentMethodRequest paymentMethodRequest);

    Optional<PaymentMethod> getPaymentMethodById(Integer id);

    List<PaymentMethod> getPaymentMethods();

    PaymentMethod updatePaymentMethod(Integer id, PaymentMethodRequest paymentMethodRequest) throws ExistingException;

    void deletePaymentMethod(Integer id);
}
