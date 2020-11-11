package com.hm2t.quizbugs.service.exam.impl;

import com.hm2t.quizbugs.model.exam.UserAnswer;
import com.hm2t.quizbugs.repository.UserAnswerRepository;
import com.hm2t.quizbugs.service.exam.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Override
    public Iterable<UserAnswer> findAll() {
        return userAnswerRepository.findAll();
    }

    @Override
    public Optional<UserAnswer> findById(Long id) {
        return userAnswerRepository.findById(id);
    }

    @Override
    public UserAnswer save(UserAnswer model) {
        return userAnswerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        userAnswerRepository.deleteById(id);
    }
}
