package com.hm2t.quizbugs.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 50)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 50)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> roles;
}
