package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.exam.Exam;
import com.hm2t.quizbugs.model.exam.UserAnswer;
import com.hm2t.quizbugs.model.exam.UserExam;
import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.answer.AnswerServiceImpl;
import com.hm2t.quizbugs.service.exam.ExamService;
import com.hm2t.quizbugs.service.exam.impl.ExamServiceImpl;
import com.hm2t.quizbugs.service.exam.impl.UserExamServiceImpl;
import com.hm2t.quizbugs.service.questions.QuestionServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/userExams")
public class UserExamController {
    private final double MARK = 10;


    private Set<UserAnswer> userAnswers;

    @Autowired
    UserExamServiceImpl userExamService;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    AnswerServiceImpl answerService;
    @Autowired
    QuestionServiceImpl questionsService;
    @Autowired
    ExamServiceImpl examService;


    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Iterable<UserExam>> getExamResultOfUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            AppUser curUser = userService.findByUsername(((UserDetails) principal).getUsername());
            Iterable<UserExam> allByUser = userExamService.findAllByUser(curUser);
            return new ResponseEntity<>(allByUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserExam> createExamForUser(@RequestBody UserExam userExam) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser currentUser = userService.findByUsername(((UserDetails) principal).getUsername());
        Optional<Exam> currentExam = examService.findById(userExam.getExam().getId());
        Set<UserAnswer> userAnswers = userExam.getUserAnswers();
        double lengthQuestions = currentExam.get().getQuestionSet().size();
        double OneTrueQuestion = 1 / lengthQuestions;
        double userPoint = 0;
        for (UserAnswer uA : userAnswers) {
            Optional<Answer> answer = answerService.findById(uA.getAnswer().getId());
            Question question = answer.get().getQuestion();
            boolean onlyTrue = question.getType() == 2 || question.getType() == 0;
            if (onlyTrue) {
                if (answer.get().isStatus())
                    userPoint += OneTrueQuestion;
            } else {
                double truePoint = 0;
                double wrongAnswer = 0;
                Question currentQuestion = answer.get().getQuestion();
                for (Answer a : currentQuestion.getAnswers()) {
                    if (a.isStatus())
                        truePoint++;
                }
                if (answer.get().isStatus())
                    userPoint += (1 / truePoint) / OneTrueQuestion;
                else
                    userPoint -= (1 / truePoint) / OneTrueQuestion;
            }
        }
        userExam.setMark(userPoint);
        userExam.setUser(currentUser);
        return new ResponseEntity<>(userExamService.save(userExam), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getAllDoneExam() {
        return new ResponseEntity<>(userExamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getUserExamById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userExamService.findById(id), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getAllExamsOfUserById(@PathVariable("id") Long id) {
        Optional<AppUser> currentUser = userService.findById(id);
        return new ResponseEntity<>(userExamService.findAllByUser(currentUser.get()), HttpStatus.OK);
    }

    @GetMapping("exams/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getAllUserExamByExamId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userExamService.getAllByExamId(id),HttpStatus.OK);
    }
}
