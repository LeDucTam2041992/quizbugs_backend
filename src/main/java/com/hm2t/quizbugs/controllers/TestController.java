package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.Test.Test;
import com.hm2t.quizbugs.model.Test.UserAnswer;
import com.hm2t.quizbugs.model.Test.UserTest;
import com.hm2t.quizbugs.service.Test.impl.TestServiceImpl;
import com.hm2t.quizbugs.service.Test.impl.UserAnswerServiceImpl;
import com.hm2t.quizbugs.service.Test.impl.UserTestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    TestServiceImpl testService;

    @Autowired
    UserTestServiceImpl userTestService;

    @Autowired
    UserAnswerServiceImpl userAnswerService;

    @GetMapping
    public Iterable<Test> getAllTests(){
        return testService.findAllByEnabledTrue();
    }

    @PostMapping
    public Test createTest(@RequestBody Test test){
        return testService.save(test);
    }

    @GetMapping("/userTest")
    public Iterable<UserTest> getAllUserTests(){
        return userTestService.findAll();
    }

    @PostMapping("/userTest")
    public UserTest createAnswer(@RequestBody UserTest userTest){
        UserTest userTest1 = userTestService.save(userTest);
        return userTest1;
    }
}
