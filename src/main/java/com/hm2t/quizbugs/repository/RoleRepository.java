package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.users.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole,Long> {
    AppRole getAppRoleByName (String roleName);
}
