package com.hm2t.quizbugs.model.Test;

import com.hm2t.quizbugs.model.questions.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Question> questionSet;
}
