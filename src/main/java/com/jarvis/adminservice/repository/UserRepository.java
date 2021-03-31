package com.jarvis.adminservice.repository;

import com.jarvis.adminservice.entity.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
