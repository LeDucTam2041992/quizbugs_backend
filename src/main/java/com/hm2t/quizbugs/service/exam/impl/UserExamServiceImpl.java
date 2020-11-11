package com.hm2t.quizbugs.service.exam.impl;

import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.repository.UserExamRepository;
import com.hm2t.quizbugs.service.exam.UserExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserExamServiceImpl implements UserExamService {
    @Autowired
    UserExamRepository userExamRepository;

    @Override
    public Iterable<com.hm2t.quizbugs.model.exam.UserExam> findAll() {
        return userExamRepository.findAll();
    }

    @Override
    public Optional<com.hm2t.quizbugs.model.exam.UserExam> findById(Long id) {
        return userExamRepository.findById(id);
    }

    @Override
    public com.hm2t.quizbugs.model.exam.UserExam save(com.hm2t.quizbugs.model.exam.UserExam model) {
        return userExamRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        userExamRepository.deleteById(id);
    }

    @Override
    public Iterable<com.hm2t.quizbugs.model.exam.UserExam> findAllByUser(AppUser user) {
        return userExamRepository.findAllByUserOrderByDate(user);
    }
}
