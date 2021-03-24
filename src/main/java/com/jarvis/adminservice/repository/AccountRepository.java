package com.jarvis.adminservice.repository;

import com.jarvis.adminservice.entity.Account;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account> {

    Optional<Account> findByUsername(@Param("username") String username);
    Optional<Account> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
