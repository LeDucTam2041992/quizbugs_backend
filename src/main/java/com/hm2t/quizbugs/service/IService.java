package com.hm2t.quizbugs.service;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> findAll();

    Optional<T> findById(long id);

    T save(T model);

    void remove(long id);
}
