package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.service.answer.AnswerService;
import com.hm2t.quizbugs.service.questions.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @GetMapping()
    public ResponseEntity<Iterable<Question>> getAllQuestion() {
        Iterable<Question> questions = questionService.findAllByIsEnabled(1);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable("id") long id) {
        return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createQuestion(@Validated @RequestBody Question question, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questionService.save(question), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editQuestion(@Validated @RequestBody Question question, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Optional<Question> currentQuestion = questionService.findById(id);
        if (currentQuestion.isPresent()) {
            currentQuestion.get().setIsEnabled(0);
            questionService.save(question);
        }
        return new ResponseEntity<>(questionService.save(question), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") long id) {
        Optional<Question> question = questionService.findById(id);
        question.get().setIsEnabled(0);
        questionService.save(question.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
