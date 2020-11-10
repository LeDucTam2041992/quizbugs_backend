package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.Test.UserAnswer;
import com.hm2t.quizbugs.model.Test.UserTest;
import com.hm2t.quizbugs.model.users.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserTestRepository extends JpaRepository<UserTest,Long> {
    Iterable<UserTest> findAllByUserOrderByDate(AppUser user);
 }
