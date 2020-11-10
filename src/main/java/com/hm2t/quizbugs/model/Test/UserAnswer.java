package com.hm2t.quizbugs.model.Test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hm2t.quizbugs.model.questions.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long answerId;

    private String answerValue;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
//    @JoinColumn(name = "user_test_id")
//    private UserTest userTest;
}
