package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.users.AppRole;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.users.Impl.RoleServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserTokenServiceImpl userTokenService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public void createUser(@RequestBody AppUser appUser) throws ValidationException {
        int passwordLength = appUser.getPassword().length();
        if (passwordLength > 5 && passwordLength < 20) {
            Set<AppRole> appRoleSet = new HashSet<>();
            appRoleSet.add((roleService.getRoleByName("ROLE_USER")));
            appRoleSet.add((roleService.getRoleByName("ROLE_ADMIN")));
            appUser.setRoles(appRoleSet);
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userService.save(appUser);
        } else {
            throw new ValidationException("pass word length must be between 5 and 20");
        }
    }

    @PutMapping("/{id}")
    public void doUpdatePassword(@RequestBody AppUser appUser,
                                 @PathVariable("id") Long id) {
        this.userService.findById(id).ifPresent((currentUser) -> {
            currentUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            this.userService.save(currentUser);
        });
    }

    @GetMapping("{id}")
    public AppUser getUserDetails(@PathVariable("id") Long id) {
        Optional<AppUser> appUser = this.userService.findById(id);
        if (appUser.isPresent()){
            return appUser.get();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @GetMapping("/logout")
    public void doLogout(HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
            userTokenService.removeAppToken(token);
        }
    }
}
