package com.hm2t.quizbugs.service.exam;

import com.hm2t.quizbugs.model.exam.Exam;
import com.hm2t.quizbugs.service.IService;

public interface ExamService extends IService<Exam> {
    Iterable<Exam> findAllByEnabledTrue();
}
