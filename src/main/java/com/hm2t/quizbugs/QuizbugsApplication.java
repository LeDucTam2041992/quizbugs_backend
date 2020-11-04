package com.hm2t.quizbugs;

import com.hm2t.quizbugs.service.questions.QuestionService;
import com.hm2t.quizbugs.service.questions.QuestionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizbugsApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizbugsApplication.class, args);
    }

}
