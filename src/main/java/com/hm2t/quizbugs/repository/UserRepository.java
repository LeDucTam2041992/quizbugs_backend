package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.users.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<AppUser,Long> {

    AppUser findAppUserByUsername(String username);
}
