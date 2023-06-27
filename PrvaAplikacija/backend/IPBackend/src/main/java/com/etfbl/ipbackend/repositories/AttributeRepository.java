package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
    Optional<Attribute> findById(Integer id);
    List<Attribute> findAll();
    List<Attribute> getAttributesByFkCategoryId(Integer id);
    Attribute save(Attribute attribute);
    void deleteById(Integer id);
}
