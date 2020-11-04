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
        Iterable<Question> questions = questionService.findAll();
//        for (Question q:questions) {
//            Iterable<Answer> answers = answerService.findAllByQuestion(q);
//            List<Answer> answerList = new ArrayList<>();
//            for (Answer a:answers) {
//                answerList.add(a);
//            }
//            q.setAnswers(answerList);
//        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable("id") long id) {
        Optional<Question> question = questionService.findById(id);
        Iterable<Answer> answers = answerService.findAllByQuestion(question.get());
        List<Answer> answerList = new ArrayList<>();
        for (Answer a:answers) {
            answerList.add(a);
        }
        question.get().setAnswers(answerList);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createQuestion(@Validated @RequestBody Question question, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Question qs = questionService.save(question);
        Iterable<Answer> answers = qs.getAnswers();
        for (Answer a:answers) {
            a.setQuestion(qs);
            answerService.save(a);
        }
        return new ResponseEntity<>(questionService.findById(qs.getId()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editQuestion(@Validated @RequestBody Question question, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        questionService.save(question);
        List<Answer> answers = question.getAnswers();
        for (Answer a:answers) {
            answerService.save(a);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") long id) {
        Optional<Question> question = questionService.findById(id);
        question.get().setStatus(0);
        questionService.save(question.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
