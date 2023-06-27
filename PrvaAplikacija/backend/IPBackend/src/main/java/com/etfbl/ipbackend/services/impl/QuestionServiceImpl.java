package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Question;
import com.etfbl.ipbackend.models.requests.QuestionRequest;
import com.etfbl.ipbackend.repositories.QuestionRepository;
import com.etfbl.ipbackend.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(QuestionRequest questionRequest) {
        Question question = new Question();
        question.setFkProduct(questionRequest.getFkProduct());
        question.setFkUserKo(questionRequest.getFkUserKo());
        question.setFkUserKome(questionRequest.getFkUserKome());
        question.setValue(questionRequest.getValue());

        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> getQuestionById(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionByProductId(Integer id) {
        return questionRepository.getQuestionsByFkProduct(id);
    }

    @Override
    public List<Question> getQuestionForUser(Integer id) {
        return questionRepository.getQuestionsByFkUserKome(id);
    }

    @Override
    public Question updateQuestion(Integer id, QuestionRequest questionRequest) throws ExistingException {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isEmpty())
            throw new ExistingException("Question sa ID: " + id + " ne postoji u bazi pdoataka");

        Question question = questionOptional.get();
        question.setFkProduct(questionRequest.getFkProduct());
        question.setFkUserKo(questionRequest.getFkUserKo());
        question.setFkUserKome(questionRequest.getFkUserKome());
        question.setValue(questionRequest.getValue());
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
