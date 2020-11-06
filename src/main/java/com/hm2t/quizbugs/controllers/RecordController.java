package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.model.record.Record;
import com.hm2t.quizbugs.model.record.RecordQuestion;
import com.hm2t.quizbugs.service.record.RecordService;
import com.hm2t.quizbugs.service.recordquestion.RecordQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/records")
@CrossOrigin("*")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordQuestionService recordQuestionService;

    @GetMapping()
    public ResponseEntity<Iterable<Record>> getAllRecord() {
        Iterable<Record> records = recordService.findAll();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Record>> getRecordById(@PathVariable("id") long id) {
        return new ResponseEntity<>(recordService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createRecord(@RequestBody Record record) {
        return new ResponseEntity<>(recordService.save(record), HttpStatus.OK);
    }

}
