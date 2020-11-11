package com.hm2t.quizbugs.service.exam.impl;

import com.hm2t.quizbugs.model.exam.Exam;
import com.hm2t.quizbugs.repository.ExamRepository;
import com.hm2t.quizbugs.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamRepository examRepository;

    @Override
    public Iterable<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> findById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    public Exam save(Exam model) {
        return examRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    public Iterable<Exam> findAllByEnabledTrue() {
        return examRepository.findAllByEnabledTrue();
    }
}
