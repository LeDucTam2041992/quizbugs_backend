package com.hm2t.quizbugs.service.Test.impl;

import com.hm2t.quizbugs.model.Test.UserTest;
import com.hm2t.quizbugs.repository.UserTestRepository;
import com.hm2t.quizbugs.service.Test.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTestServiceImpl implements UserTestService {
    @Autowired
    UserTestRepository userTestRepository;

    @Override
    public Iterable<UserTest> findAll() {
        return userTestRepository.findAll();
    }

    @Override
    public Optional<UserTest> findById(Long id) {
        return userTestRepository.findById(id);
    }

    @Override
    public UserTest save(UserTest model) {
        return userTestRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        userTestRepository.deleteById(id);
    }
}
