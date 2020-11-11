package com.hm2t.quizbugs.service.catogories;

import com.hm2t.quizbugs.model.questions.Category;
import com.hm2t.quizbugs.service.IService;

public interface CategoryService extends IService<Category> {
    Iterable<Category> findAllByIsEnabledTrue();
}
