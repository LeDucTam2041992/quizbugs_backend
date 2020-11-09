package com.hm2t.quizbugs.model.Test;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hm2t.quizbugs.model.questions.Answer;
import com.hm2t.quizbugs.model.users.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date date;

    private Double mark;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "userTest_id")
    private Set<UserAnswer> userAnswers;
}
