package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Answer;
import com.etfbl.ipbackend.models.requests.AnswerRequest;
import com.etfbl.ipbackend.services.AnswerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;
    private static final Logger logger = LogManager.getLogger(AnswerController.class);

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody AnswerRequest answerRequest) {
        Answer answer = answerService.createAnswer(answerRequest);
        logger.info("Kreiran je odgovor.");
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Answer> getAnswerForQuestionId(@PathVariable("questionId") Integer id) {
        Answer answer = answerService.getAnswerForQuestionId(id);

        return ResponseEntity.ok(answer);
    }
    @GetMapping("/exist/{questionId}")
    public boolean existAnswerWithQuestionId(@PathVariable("questionId") Integer id) {
        return answerService.existAnswerWithQuestion(id);
    }
}
