package com.hm2t.quizbugs.service.questions;

import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.service.IService;

public interface QuestionService extends IService<Question> {
    Iterable<Question> findAllByIsEnabled();
}
