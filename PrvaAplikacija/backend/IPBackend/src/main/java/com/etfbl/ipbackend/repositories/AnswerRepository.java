package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Optional<Answer> findById(Integer id);
    List<Answer> findAll();
    List<Answer> getAnswerByFkUserAnsKome(Integer id);
    boolean existsAnswerByFkQuestion(Integer id);
    Answer getAnswerByFkQuestion(Integer id);
    Answer save(Answer answer);
    void deleteById(Integer id);
}
