package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.service.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping()
    public ResponseEntity<?> createAnswers(@Validated @RequestBody Answer answers, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        answerService.save(answers);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
