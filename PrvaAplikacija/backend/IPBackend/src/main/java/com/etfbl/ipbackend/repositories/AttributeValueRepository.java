package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {
    Optional<AttributeValue> findById(Integer id);
    List<AttributeValue> findAll();
    List<AttributeValue> getAttributeValueByFkProizvod(Integer id);
    AttributeValue save(AttributeValue attributeValue);
    void deleteById(Integer id);
}
