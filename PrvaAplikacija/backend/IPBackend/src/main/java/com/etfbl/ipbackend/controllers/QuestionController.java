package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Question;
import com.etfbl.ipbackend.models.requests.QuestionRequest;
import com.etfbl.ipbackend.services.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private static final Logger logger = LogManager.getLogger(QuestionController.class);

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionRequest questionRequest) {
        Question question = questionService.createQuestion(questionRequest);
        logger.info("POST created new question");
        return ResponseEntity.ok(question);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Question>> getAllQuestionForProduct(@PathVariable("productId") Integer id) {
        List<Question> questions = questionService.getQuestionByProductId(id);

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Question>> getAllQuestionForUser(@PathVariable("userId") Integer id) {
        List<Question> questions = questionService.getQuestionForUser(id);

        return ResponseEntity.ok(questions);
    }
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("questionId") Integer id) {
        Optional<Question> questionOptional = questionService.getQuestionById(id);
        if (questionOptional.isEmpty())
            return ResponseEntity.noContent().build();
        Question question = questionOptional.get();
        return ResponseEntity.ok(question);
    }
}
