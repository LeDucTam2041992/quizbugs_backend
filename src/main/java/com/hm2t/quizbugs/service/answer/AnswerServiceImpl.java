package com.hm2t.quizbugs.service.answer;

import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Iterable<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> findById(long id) {
        return answerRepository.findById(id);
    }

    @Override
    public Answer save(Answer model) {
        return answerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public Iterable<Answer> findAllByQuestion(Question question) {
        return answerRepository.findAllByQuestion(question);
    }
}
