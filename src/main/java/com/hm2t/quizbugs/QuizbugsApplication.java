package com.hm2t.quizbugs;

import com.hm2t.quizbugs.model.users.AppRole;
import com.hm2t.quizbugs.service.users.Impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizbugsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuizbugsApplication.class, args);
    }

    @Autowired
    RoleServiceImpl roleService;

    @Override
    public void run(String... args) throws Exception {
        Iterable<AppRole> allRole = roleService.findAll();
        if(!allRole.iterator().hasNext()) {
            roleService.save(new AppRole(1L,"ROLE_ADMIN"));
            roleService.save(new AppRole(2L,"ROLE_USER"));
        }
    }

}
