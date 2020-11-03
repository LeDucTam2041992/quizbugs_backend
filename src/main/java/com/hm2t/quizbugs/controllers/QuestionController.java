package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.service.questions.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public ResponseEntity<Iterable<Question>> getAllQuestion() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable("id") long id) {
        return ResponseEntity.ok(questionService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createQuestion(@Validated @RequestBody Question question, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        questionService.save(question);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editQuestion(@Validated @RequestBody Question question, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        questionService.save(question);
        return ResponseEntity.ok("Success");
    }
}
