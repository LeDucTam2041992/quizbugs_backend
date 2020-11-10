package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.Test.Test;
import com.hm2t.quizbugs.model.Test.UserTest;
import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.Test.impl.TestServiceImpl;
import com.hm2t.quizbugs.service.Test.impl.UserAnswerServiceImpl;
import com.hm2t.quizbugs.service.Test.impl.UserTestServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    TestServiceImpl testService;

    @Autowired
    UserTestServiceImpl userTestService;

    @Autowired
    UserAnswerServiceImpl userAnswerService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<?> getAllTests(){
        return new ResponseEntity<>(testService.findAllByEnabledTrue(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTest(@Validated @RequestBody Test test, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        testService.findById(1L).get();
        return new ResponseEntity<>(testService.save(test), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTest(@Validated @RequestBody Test test,
                                        BindingResult bindingResult,
                                        @PathVariable("id") Long id){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Optional<Test> currentTest = testService.findById(id);
        if (currentTest.isPresent()) {
            currentTest.get().setEnabled(false);
            Test testSaved = testService.save(test);
            return new ResponseEntity<>(testSaved, HttpStatus.OK);
        }
        return new ResponseEntity<>(currentTest, HttpStatus.BAD_REQUEST);
    }

}
