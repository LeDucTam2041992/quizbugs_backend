package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Iterable<Answer> findAllByQuestion(Question question);
}
