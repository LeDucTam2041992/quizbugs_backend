package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.model.users.AppUserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserTokenRepository extends JpaRepository<AppUserToken,Long> {
    void removeAllByAppUser(AppUser appUser);

    boolean existsAppUserTokenByToken(String token);

    void removeByToken(String token);
}
