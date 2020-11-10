package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Iterable<Question> findAllByIsEnabled(int isEnabled);
}
