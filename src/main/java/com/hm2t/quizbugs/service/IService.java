package com.hm2t.quizbugs.service;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> findAll();

    Optional<T> findById(long id);

    void save(T model);

    void remove(Long id);
}
