package com.hm2t.quizbugs.model.users;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> roles;
}
