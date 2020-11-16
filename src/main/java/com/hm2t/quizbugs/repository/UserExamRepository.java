package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.exam.UserExam;
import com.hm2t.quizbugs.model.users.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserExamRepository extends JpaRepository<com.hm2t.quizbugs.model.exam.UserExam,Long> {
    Iterable<UserExam> findAllByUserOrderByDate(AppUser user);
    List<UserExam> getAllByExamId(Long id);
 }
