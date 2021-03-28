package com.jarvis.adminservice.service;

import com.jarvis.adminservice.entity.Role;
import com.jarvis.adminservice.repository.GenericRepository;
import com.jarvis.adminservice.repository.RoleRepository;
import com.jarvis.adminservice.request.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role, RoleRequest> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role entity() {
        return new Role();
    }

    @Override
    public GenericRepository<Role> repository() {
        return roleRepository;
    }
}
