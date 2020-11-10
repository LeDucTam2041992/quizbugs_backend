package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.exam.Exam;
import com.hm2t.quizbugs.service.exam.impl.ExamServiceImpl;
import com.hm2t.quizbugs.service.exam.impl.UserAnswerServiceImpl;
import com.hm2t.quizbugs.service.exam.impl.UserExamServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    ExamServiceImpl examService;

    @Autowired
    UserExamServiceImpl userTestService;

    @Autowired
    UserAnswerServiceImpl userAnswerService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<?> getAllTests(){
        return new ResponseEntity<>(examService.findAllByEnabledTrue(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createExam(@Validated @RequestBody Exam exam, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        examService.findById(1L).get();
        return new ResponseEntity<>(examService.save(exam), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExam(@Validated @RequestBody Exam exam,
                                        BindingResult bindingResult,
                                        @PathVariable("id") Long id){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Optional<Exam> currentTest = examService.findById(id);
        if (currentTest.isPresent()) {
            currentTest.get().setEnabled(false);
            Exam examSaved = examService.save(exam);
            return new ResponseEntity<>(examSaved, HttpStatus.OK);
        }
        return new ResponseEntity<>(currentTest, HttpStatus.BAD_REQUEST);
    }

}
