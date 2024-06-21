package com.venusSchoolManagementSystem.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venusSchoolManagementSystem.Entity.Roles;
import com.venusSchoolManagementSystem.Repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Roles saveRole(Roles role) {
        return roleRepository.save(role);
    }

    public Roles findByName(String name) {
        return roleRepository.findByName(name);
    }
}
