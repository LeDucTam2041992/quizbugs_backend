package com.hm2t.quizbugs.service.Test;

import com.hm2t.quizbugs.model.Test.UserTest;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.IService;

public interface UserTestService extends IService<UserTest> {
    Iterable<UserTest> findAllByUser(AppUser user);
}
