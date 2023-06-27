package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Purchase;
import com.etfbl.ipbackend.models.Question;
import com.etfbl.ipbackend.models.requests.PurchaseRequest;
import com.etfbl.ipbackend.models.requests.QuestionRequest;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question createQuestion(QuestionRequest questionRequest);

    Optional<Question> getQuestionById(Integer id);

    List<Question> getQuestions();
    List<Question> getQuestionByProductId(Integer id);
    List<Question> getQuestionForUser(Integer id);
    Question updateQuestion(Integer id, QuestionRequest questionRequest) throws ExistingException;

    void deleteQuestion(Integer id);
}
