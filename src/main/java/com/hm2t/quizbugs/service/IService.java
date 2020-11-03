package com.hm2t.quizbugs.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    void save(T category);

    void remove(Long id);
}
