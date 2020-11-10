package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {
    Iterable<Exam> findAllByEnabledTrue();
}
