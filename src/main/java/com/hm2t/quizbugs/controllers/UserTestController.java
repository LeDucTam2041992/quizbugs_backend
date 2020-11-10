package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.Test.UserTest;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.Test.impl.UserTestServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userTest")
public class UserTestController {
    @Autowired
    UserTestServiceImpl userTestService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Iterable<UserTest>> getUserTestByUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            AppUser curUser = userService.findByUsername(((UserDetails) principal).getUsername());
            Iterable<UserTest> allByUser = userTestService.findAllByUser(curUser);
            return new ResponseEntity<>(allByUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserTest> createUserTest(@RequestBody UserTest userTest){
        UserTest useResult = userTestService.save(userTest);
        return new ResponseEntity<>(useResult,HttpStatus.OK);
    }

}
