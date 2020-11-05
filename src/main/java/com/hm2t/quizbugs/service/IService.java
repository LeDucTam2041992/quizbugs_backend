package com.hm2t.quizbugs.service;

import com.hm2t.quizbugs.model.questions.Question;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> findAll();

    Optional<T> findById(long id);

    Question save(T model);

    void remove(Long id);
}
