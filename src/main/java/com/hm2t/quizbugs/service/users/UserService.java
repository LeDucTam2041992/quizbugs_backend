package com.hm2t.quizbugs.service.users;

import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService extends IService<AppUser> {
    UserDetails loadUserDetailById(Long id);

    AppUser findByUsername(String username);

}
