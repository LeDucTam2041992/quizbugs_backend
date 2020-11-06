package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.record.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Long> {
}
