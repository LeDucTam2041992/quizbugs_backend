package com.hm2t.quizbugs.service.answer;

import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.service.IService;

public interface AnswerService extends IService<Answer> {
    Iterable<Answer> findAllByQuestion(Question question);
}
