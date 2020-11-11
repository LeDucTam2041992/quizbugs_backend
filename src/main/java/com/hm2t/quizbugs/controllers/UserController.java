package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.config.jwt.model.UpdatePassword;
import com.hm2t.quizbugs.model.users.AppRole;
import com.hm2t.quizbugs.model.users.AppUser;
import com.hm2t.quizbugs.service.users.Impl.RoleServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserTokenServiceImpl;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

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
            appUser.setRoles(appRoleSet);
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userService.save(appUser);
        } else {
            throw new ValidationException("pass word length must be between 5 and 20");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            AppUser currentUser = userService.findByUsername(userDetails.getUsername());
            boolean isAdmin = false;
            for (AppRole obj : currentUser.getRoles()) {
                if (obj.getName().equals("ROLE_ADMIN")) {
                    isAdmin = true;
                    break;
                }
            }
            if (isAdmin) {
                return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Nothing", HttpStatus.BAD_REQUEST);
    }
        @PutMapping("/updatePassword")
        public void doUpdatePassword (@RequestBody UpdatePassword updatePassword) throws PasswordException {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                boolean matches = passwordEncoder.matches(updatePassword.getOldPassword(), userDetails.getPassword());
                if (matches) {
                    AppUser currentUser = userService.findByUsername(userDetails.getUsername());
                    currentUser.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
                    userService.save(currentUser);
                } else {
                    throw new PasswordException("password not match");
                }
            }
        }

        @GetMapping("{id}")
        public AppUser getUserDetails (@PathVariable("id") Long id){
            Optional<AppUser> appUser = this.userService.findById(id);
            if (appUser.isPresent()) {
                return appUser.get();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @PutMapping("{id}")
        public ResponseEntity<?> setAdmin (@PathVariable("id") Long id){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                AppUser currentUser = userService.findByUsername(userDetails.getUsername());
                boolean isAdmin = false;
                for (AppRole obj : currentUser.getRoles()) {
                    if (obj.getName().equals("ROLE_ADMIN")) {
                        isAdmin = true;
                        break;
                    }
                }
                if (isAdmin) {
                    Optional<AppUser> updateUser = this.userService.findById(id);
                    AppRole role = new AppRole();
                    role.setId(1L);
                    role.setName("ROLE_ADMIN");
                    updateUser.get().getRoles().add(role);
                    userService.save(updateUser.get());
                }
            }
            return new ResponseEntity<>("Hello, Word!", HttpStatus.OK);
        }

        @GetMapping("/logout")
        public void doLogout (HttpServletRequest request){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
                userTokenService.removeAppToken(token);
            }
        }
    }
