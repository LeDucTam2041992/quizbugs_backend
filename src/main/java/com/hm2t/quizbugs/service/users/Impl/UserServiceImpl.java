package com.hm2t.quizbugs.service.users.Impl;

import com.hm2t.quizbugs.config.jwt.model.CustomUserDetail;
import com.hm2t.quizbugs.model.questions.Question;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.repository.UserRepository;
import com.hm2t.quizbugs.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<AppUser> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Question save(AppUser model) {
       return this.userRepository.save(model);
    }

    @Override
    public void remove(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findAppUserByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(appUser);
    }

    @Override
    public UserDetails loadUserDetailById(Long id) {
        AppUser appUser = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return new CustomUserDetail(appUser);
    }
}
