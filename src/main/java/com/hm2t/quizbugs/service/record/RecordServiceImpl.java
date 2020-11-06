package com.hm2t.quizbugs.service.record;

import com.hm2t.quizbugs.model.record.Record;
import com.hm2t.quizbugs.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;
    @Override
    public Iterable<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Optional<Record> findById(long id) {
        return recordRepository.findById(id);
    }

    @Override
    public Record save(Record model) {
        return recordRepository.save(model);
    }

    @Override
    public void remove(long id) {

    }
}
