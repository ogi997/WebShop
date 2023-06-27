package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Answer;
import com.etfbl.ipbackend.models.requests.AnswerRequest;
import com.etfbl.ipbackend.repositories.AnswerRepository;
import com.etfbl.ipbackend.services.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer createAnswer(AnswerRequest answerRequest) {
        Answer answer = new Answer();
        answer.setFkQuestion(answerRequest.getFkQuestion());
        answer.setFkUserAnsKo(answerRequest.getFkUserAnsKo());
        answer.setFkUserAnsKome(answerRequest.getFkUserAnsKome());
        answer.setValue(answerRequest.getValue());

        return answerRepository.save(answer);
    }

    @Override
    public Optional<Answer> getAnswerById(Integer id) {
        return answerRepository.findById(id);
    }

    @Override
    public Answer getAnswerForQuestionId(Integer id) {
        return answerRepository.getAnswerByFkQuestion(id);
    }

    @Override
    public boolean existAnswerWithQuestion(Integer id) {
        return answerRepository.existsAnswerByFkQuestion(id);
    }

    @Override
    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public List<Answer> getAnswerForUser(Integer id) {
        return answerRepository.getAnswerByFkUserAnsKome(id);
    }

    @Override
    public Answer updateAnswer(Integer id, AnswerRequest answerRequest) throws ExistingException {
        Optional<Answer> answerOptional = answerRepository.findById(id);

        if (answerOptional.isEmpty())
            throw new ExistingException("Answer sa ID: " + id + " ne postoji u bazi pdoataka");

        Answer answer = answerOptional.get();
        answer.setFkQuestion(answerRequest.getFkQuestion());
        answer.setFkUserAnsKo(answerRequest.getFkUserAnsKo());
        answer.setFkUserAnsKome(answerRequest.getFkUserAnsKome());
        answer.setValue(answerRequest.getValue());
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }
}
