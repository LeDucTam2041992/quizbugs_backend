package com.hm2t.quizbugs.service.questions;

import com.hm2t.quizbugs.model.questions.Category;
import com.hm2t.quizbugs.service.IService;

public interface CategoryService extends IService<Category> {
    Long findTopOrderByIdDesc();
}
