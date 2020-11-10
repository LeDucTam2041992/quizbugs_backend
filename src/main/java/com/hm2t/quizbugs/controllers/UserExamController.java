package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.exam.UserExam;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.exam.impl.UserExamServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userExams")
public class UserExamController {
    @Autowired
    UserExamServiceImpl examService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Iterable<UserExam>> getExamResultOfUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            AppUser curUser = userService.findByUsername(((UserDetails) principal).getUsername());
            Iterable<UserExam> allByUser = examService.findAllByUser(curUser);
            return new ResponseEntity<>(allByUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserExam> createExamForUser(@RequestBody UserExam userExam){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser currentUser = userService.findByUsername(((UserDetails) principal).getUsername());
        userExam.setUser(currentUser);
        UserExam useResult = examService.save(userExam);
        return new ResponseEntity<>(useResult,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDoneExam(){
        return new ResponseEntity<>(examService.findAll(),HttpStatus.OK);
    }

}
