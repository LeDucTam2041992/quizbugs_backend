package com.hm2t.quizbugs.model.questions;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "Text")
    @NotNull
    @NotBlank
    private String answer;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
