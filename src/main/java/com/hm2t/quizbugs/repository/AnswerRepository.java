package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {
    Iterable<Answer> findAllByQuestion(Question question);
}
