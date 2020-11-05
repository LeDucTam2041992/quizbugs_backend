package com.hm2t.quizbugs.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser appUser;

    @Column(length = 350)
    private String token;

    public AppUserToken(AppUser appUser, String token) {
        this.appUser = appUser;
        this.token = token;
    }

}
