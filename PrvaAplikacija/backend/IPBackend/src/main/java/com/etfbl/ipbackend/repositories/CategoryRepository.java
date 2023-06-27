package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(Integer id);
    List<Category> findAllByDeleted(Byte deleted);
    Category save(Category category);
    void deleteById(Integer id);
}
