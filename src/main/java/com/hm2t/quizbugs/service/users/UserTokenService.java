package com.hm2t.quizbugs.service.users;


import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.model.users.AppUserToken;
import com.hm2t.quizbugs.service.IService;

public interface UserTokenService extends IService<AppUserToken> {
    void removeAllByAppUser(AppUser appUser);
    boolean isTokenExists(String token);
    void removeAppToken(String token) ;
}
