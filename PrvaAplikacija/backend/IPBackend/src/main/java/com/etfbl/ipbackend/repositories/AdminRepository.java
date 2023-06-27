package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findById(Integer id);
    List<Admin> findAll();
    Admin save(Admin admin);
    void deleteById(Integer id);
}
