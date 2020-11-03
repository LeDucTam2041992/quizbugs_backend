package com.hm2t.quizbugs.service.users;

import com.hm2t.quizbugs.model.users.AppRole;
import com.hm2t.quizbugs.service.IService;

public interface RoleService extends IService<AppRole> {
    AppRole getRoleByName(String roleName);
}
