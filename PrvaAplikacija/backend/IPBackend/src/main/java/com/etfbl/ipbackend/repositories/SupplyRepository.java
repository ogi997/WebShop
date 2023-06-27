package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Supply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Integer> {
    Optional<Supply> findById(Integer id);
    Optional<Supply> findByFkProd(Integer id);
    List<Supply> findAll();


    Page<Supply> getSupplyByActive(Pageable pageable, Integer active);

    List<Supply> getSupplyByActive(Integer active);
    Supply save(Supply supply);
    void deleteById(Integer id);
}
