package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.Test.Test;
import com.hm2t.quizbugs.model.Test.UserTest;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.Test.impl.TestServiceImpl;
import com.hm2t.quizbugs.service.Test.impl.UserAnswerServiceImpl;
import com.hm2t.quizbugs.service.Test.impl.UserTestServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Iterable<Test> getAllTests(){
        return testService.findAll();
    }

    @PostMapping
    public Test createTest(@RequestBody Test test){
        return testService.save(test);
    }
}
