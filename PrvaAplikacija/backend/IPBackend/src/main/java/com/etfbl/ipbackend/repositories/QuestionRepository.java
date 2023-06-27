package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findById(Integer id);
    List<Question> findAll();
    List<Question> getQuestionsByFkProduct(Integer id);
    List<Question> getQuestionsByFkUserKome(Integer id);
    Question save(Question question);
    void deleteById(Integer id);
}
