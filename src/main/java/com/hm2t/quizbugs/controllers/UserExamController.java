package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.exam.UserAnswer;
import com.hm2t.quizbugs.model.exam.UserExam;
import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.answer.AnswerServiceImpl;
import com.hm2t.quizbugs.service.exam.impl.UserExamServiceImpl;
import com.hm2t.quizbugs.service.questions.QuestionServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/userExams")
public class UserExamController {
   private final double MARK =10;


   private Set<UserAnswer> userAnswers;

    @Autowired
    UserExamServiceImpl userExamService;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    AnswerServiceImpl answerService;
    @Autowired
    QuestionServiceImpl questionsService;




    @GetMapping
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<Iterable<UserExam>> getExamResultOfUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            AppUser curUser = userService.findByUsername(((UserDetails) principal).getUsername());
            Iterable<UserExam> allByUser = userExamService.findAllByUser(curUser);
            return new ResponseEntity<>(allByUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<UserExam> createExamForUser(@RequestBody UserExam userExam){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser currentUser = userService.findByUsername(((UserDetails) principal).getUsername());
        Set<UserAnswer> userAnswers = userExam.getUserAnswers();
        double lengthQuestions = userExam.getExam().getQuestionSet().size();
        double OneTrueQuestion = 1/lengthQuestions;
        double userPoint = 0;
        for (UserAnswer uA : userAnswers) {
            Answer answer = answerService.findById(uA.getAnswer().getId()).get();
            if(answer.isStatus()){
                Question question = answer.getQuestion();
                boolean onlyTrue = question.getType() == 2 || question.getType() == 0;
                if(onlyTrue) {
                    userPoint+= OneTrueQuestion;
                } else {
                    int truePoint = 0;
                    for(Answer a: answer.getQuestion().getAnswers()){
                        if(a.isStatus())
                            truePoint++;
                    }
                    userPoint+=(1/truePoint)/OneTrueQuestion;
                }
            }
        }
        userExam.setMark(userPoint);
        return new ResponseEntity<>(userExam,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getAllDoneExam(){
        return new ResponseEntity<>(userExamService.findAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getUserExamById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userExamService.findById(id),HttpStatus.OK);
    }
}
