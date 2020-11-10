package com.hm2t.quizbugs.model.Test;

import com.hm2t.quizbugs.model.questions.Question;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Text")
    @NotBlank
    @NotNull
    private String name;
    @Column(columnDefinition = "boolean default true")
    private boolean enabled = true;


    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Question> questionSet;
}
