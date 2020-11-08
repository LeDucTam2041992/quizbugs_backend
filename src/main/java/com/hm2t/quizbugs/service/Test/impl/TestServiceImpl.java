package com.hm2t.quizbugs.service.Test.impl;

import com.hm2t.quizbugs.model.Test.Test;
import com.hm2t.quizbugs.repository.TestRepository;
import com.hm2t.quizbugs.service.Test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;

    @Override
    public Iterable<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }

    @Override
    public Test save(Test model) {
        return testRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        testRepository.deleteById(id);
    }
}
