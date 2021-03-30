package com.jarvis.adminservice.repository;

import com.jarvis.adminservice.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends GenericRepository<Role> {

    Optional<Role> findById(String id);
}
