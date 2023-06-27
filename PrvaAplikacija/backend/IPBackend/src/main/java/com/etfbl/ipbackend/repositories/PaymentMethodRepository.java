package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    Optional<PaymentMethod> findById(Integer id);
    List<PaymentMethod> findAll();
    PaymentMethod save(PaymentMethod paymentMethod);
    void deleteById(Integer id);
}
