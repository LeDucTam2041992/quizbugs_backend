package com.hm2t.quizbugs.service.users.Impl;


import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.model.users.AppUserToken;
import com.hm2t.quizbugs.repository.UserTokenRepository;
import com.hm2t.quizbugs.service.users.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTokenServiceImpl implements UserTokenService {
    @Autowired
    UserTokenRepository userTokenRepository;

    @Override
    public AppUserToken save(AppUserToken model) {
       return this.userTokenRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        this.userTokenRepository.deleteById(id);
    }

    @Override
    public void removeAllByAppUser(AppUser appUser) {
        this.userTokenRepository.removeAllByAppUser(appUser);
    }

    @Override
    public boolean isTokenExists(String token) {
        return this.userTokenRepository.existsAppUserTokenByToken(token);
    }

    @Override
    public void removeAppToken(String token) {
        this.userTokenRepository.removeByToken(token);
    }

    @Override
    public Iterable<AppUserToken> findAll() {
        return this.userTokenRepository.findAll();
    }

    @Override
    public Optional<AppUserToken> findById(Long id) {
        return Optional.empty();
    }


}
