package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.Test.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserTestRepository extends JpaRepository<UserTest,Long> {
}
