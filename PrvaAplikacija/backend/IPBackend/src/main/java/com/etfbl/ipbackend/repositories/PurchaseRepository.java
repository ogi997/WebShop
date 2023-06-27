package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Optional<Purchase> findById(Integer id);
    List<Purchase> findAll();
    List<Purchase> getPurchaseByFkUse(Integer id);
    Purchase save(Purchase purchase);
    void deleteById(Integer id);
}
