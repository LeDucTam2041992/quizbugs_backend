package com.hm2t.quizbugs;

import com.hm2t.quizbugs.service.answer.AnswerServiceImpl;
import com.hm2t.quizbugs.service.catogories.CategoryServiceImpl;
import com.hm2t.quizbugs.service.questions.QuestionServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.RoleServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QuizbugsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuizbugsApplication.class, args);
    }

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    AnswerServiceImpl answerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
    }

}