package com.jarvis.adminservice.repository;

import com.jarvis.adminservice.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends GenericRepository<UserRole> {

    Optional<List<UserRole>> findByUserId(long userId);
}
