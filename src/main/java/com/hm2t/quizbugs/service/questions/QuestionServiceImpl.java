package com.hm2t.quizbugs.service.questions;

import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question save(Question model) {
        return questionRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Iterable<Question> findAllByIsEnabled() {
        return questionRepository.findAllByEnabledTrue();
    }
}
