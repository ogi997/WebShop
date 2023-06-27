package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Answer;
import com.etfbl.ipbackend.models.Question;
import com.etfbl.ipbackend.models.requests.AnswerRequest;
import com.etfbl.ipbackend.models.requests.QuestionRequest;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    Answer createAnswer(AnswerRequest answerRequest);

    Optional<Answer> getAnswerById(Integer id);
    Answer getAnswerForQuestionId(Integer id);
    boolean existAnswerWithQuestion(Integer id);
    List<Answer> getAnswers();
//    List<Question> getQuestionByProductId(Integer id);
    List<Answer> getAnswerForUser(Integer id);
    Answer updateAnswer(Integer id, AnswerRequest answerRequest) throws ExistingException;

    void deleteAnswer(Integer id);
}
