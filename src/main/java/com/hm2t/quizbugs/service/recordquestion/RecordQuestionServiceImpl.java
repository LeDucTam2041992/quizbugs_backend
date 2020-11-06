package com.hm2t.quizbugs.service.recordquestion;

import com.hm2t.quizbugs.model.record.RecordQuestion;
import com.hm2t.quizbugs.repository.RecordQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RecordQuestionServiceImpl implements RecordQuestionService {
    @Autowired
    private RecordQuestionRepository recordQuestionRepository;
    @Override
    public Iterable<RecordQuestion> findAll() {
        return recordQuestionRepository.findAll();
    }

    @Override
    public Optional<RecordQuestion> findById(long id) {
        return recordQuestionRepository.findById(id);
    }

    @Override
    public RecordQuestion save(RecordQuestion model) {
        return recordQuestionRepository.save(model);
    }

    @Override
    public void remove(long id) {

    }
}
