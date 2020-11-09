package com.hm2t.quizbugs;

import com.hm2t.quizbugs.model.Test.Test;
import com.hm2t.quizbugs.model.Test.UserTest;
import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Category;
import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.model.users.AppRole;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.Test.impl.TestServiceImpl;
import com.hm2t.quizbugs.service.Test.impl.UserTestServiceImpl;
import com.hm2t.quizbugs.service.answer.AnswerServiceImpl;
import com.hm2t.quizbugs.service.questions.CategoryServiceImpl;
import com.hm2t.quizbugs.service.questions.QuestionServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.RoleServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    TestServiceImpl testService;

    @Autowired
    UserTestServiceImpl userTestService;

    @Override
    public void run(String... args) throws Exception {
        Iterable<AppRole> allRole = roleService.findAll();
        if(!allRole.iterator().hasNext()) {
            roleService.save(new AppRole(1L,"ROLE_ADMIN"));
            roleService.save(new AppRole(2L,"ROLE_USER"));
        }

        Iterable<AppUser> appUsers = userService.findAll();
        if (!appUsers.iterator().hasNext()){
            AppUser appUser = new AppUser();
            appUser.setUsername("huy8895");
            appUser.setPassword(passwordEncoder.encode("123456"));
            appUser.setRoles(Collections.singleton(roleService.findById(2L).get()));
            userService.save(appUser);

            AppUser appUser1 = new AppUser();
            appUser1.setUsername("admin123");
            appUser1.setPassword(passwordEncoder.encode("123456"));
            HashSet<AppRole> appRoles = new HashSet<>();
            appRoles.add(roleService.findById(1L).get());
            appRoles.add(roleService.findById(2L).get());
            appUser1.setRoles(appRoles);
            userService.save(appUser1);
        }

        List<Category> allCategories = categoryService.findAll();
        if (allCategories.isEmpty()){
            categoryService.save(new Category(1L,"PHP"));
            categoryService.save(new Category(2L,"JAVA"));
            categoryService.save(new Category(3L,"SQL"));
        }

        Iterable<Question> allQuestions = questionService.findAll();
        if (!allQuestions.iterator().hasNext()){
            Question question = new Question();
            question.setCategory(categoryService.findById(1L).get());
            question.setStatus(1);
            question.setType(1);
            question.setQuestion("1 + 1 = ?");
            questionService.save(question);

            Question question1 = new Question();
            question1.setCategory(categoryService.findById(1L).get());
            question1.setStatus(1);
            question1.setType(1);
            question1.setQuestion("2 + 2 = ?");
            questionService.save(question1);
        }

        Iterable<Answer> answerServiceAll = answerService.findAll();
        if (!answerServiceAll.iterator().hasNext()){
            Answer answer = new Answer();
            answer.setQuestion(questionService.findById(1L).get());
            answer.setAnswer("bang 2");
            answer.setStatus(true);
            answerService.save(answer);

            Answer answer1 = new Answer();
            answer1.setQuestion(questionService.findById(1L).get());
            answer1.setAnswer("bang 3");
            answer1.setStatus(false);
            answerService.save(answer1);

            Answer answer2 = new Answer();
            answer2.setQuestion(questionService.findById(2L).get());
            answer2.setAnswer("bang 4");
            answer2.setStatus(true);
            answerService.save(answer2);

            Answer answer3 = new Answer();
            answer3.setQuestion(questionService.findById(2L).get());
            answer3.setAnswer("bang 5");
            answer3.setStatus(false);
            answerService.save(answer3);
        }

        Iterable<Test> testServiceAll = testService.findAll();
        if (!testServiceAll.iterator().hasNext()){
            Test test = new Test();
            test.setName("JAVA_TEST");
            HashSet<Question> questionHashSet = new HashSet<>();
            questionHashSet.add(questionService.findById(1L).get());
            questionHashSet.add(questionService.findById(2L).get());
            test.setQuestionSet(questionHashSet);
            testService.save(test);

            Test test1 = new Test();
            test1.setName("PHP_TEST");
            testService.save(test1);
        }

        Iterable<UserTest> userTestServiceAll = userTestService.findAll();
        if (!userTestServiceAll.iterator().hasNext()){
            UserTest userTest1 = new UserTest();
            userTest1.setTest(testService.findById(1L).get());
            userTest1.setUser(userService.findById(1L).get());
            userTest1.setMark(10.0);

            userTestService.save(userTest1);

            UserTest userTest2 = new UserTest();
            userTest2.setTest(testService.findById(1L).get());
            userTest2.setUser(userService.findById(1L).get());
            userTest2.setMark(11.0);

            userTestService.save(userTest2);
        }
    }

}
