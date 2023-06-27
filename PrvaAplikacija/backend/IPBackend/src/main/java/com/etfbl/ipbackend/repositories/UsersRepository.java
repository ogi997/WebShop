package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findById(Integer id);
    List<Users> findAll();
    Users findByEmailAndPassword(String email, String password);
    Users getUsersByUsername(String username);
    Users getUsersByEmail(String email);
    Users save(Users users);
    void deleteById(Integer id);
}
