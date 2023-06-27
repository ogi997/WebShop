package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.SupportAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportAdminRepository extends JpaRepository<SupportAdmin, Integer> {
    Optional<SupportAdmin> findById(Integer id);
    List<SupportAdmin> findAll();
    SupportAdmin save(SupportAdmin supportAdmin);
    void deleteById(Integer id);
}
