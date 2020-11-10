package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.exam.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserAnswerRepository extends JpaRepository<UserAnswer,Long> {

}
