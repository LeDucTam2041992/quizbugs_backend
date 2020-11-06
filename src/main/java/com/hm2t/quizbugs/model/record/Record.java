package com.hm2t.quizbugs.model.record;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hm2t.quizbugs.model.users.AppUser;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "records")
@Data
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    private double point;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date date;

    @OneToMany(targetEntity = RecordQuestion.class, mappedBy = "record", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<RecordQuestion> recordQuestionList;
}
