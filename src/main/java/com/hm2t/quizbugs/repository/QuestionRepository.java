package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.questions.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
