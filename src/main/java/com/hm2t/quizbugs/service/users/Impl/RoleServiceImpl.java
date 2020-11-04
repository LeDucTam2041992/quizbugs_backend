package com.hm2t.quizbugs.service.users.Impl;

import com.hm2t.quizbugs.model.users.AppRole;
import com.hm2t.quizbugs.repository.RoleRepository;
import com.hm2t.quizbugs.service.users.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Iterable<AppRole> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Optional<AppRole> findById(long id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public AppRole save(AppRole model) {
        return this.roleRepository.save(model);
    }

    @Override
    public void remove(long id) {
        this.roleRepository.deleteById(id);
    }

    @Override
    public AppRole getRoleByName(String roleName) {
        return this.roleRepository.getAppRoleByName(roleName);
    }
}
