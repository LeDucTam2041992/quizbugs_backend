package com.hm2t.quizbugs.model.questions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
@Data
@Table(name = "category")
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Length(max = 50)
    private String category;
}
