package com.hm2t.quizbugs.model.record;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.questions.Question;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "record_questions")
@Data
public class RecordQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "record_id")
    @JsonBackReference
    private Record record;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToMany
    private List<Answer> answersOfUser;

    @ManyToMany
    private List<Answer> answersOfQuestion;
}
