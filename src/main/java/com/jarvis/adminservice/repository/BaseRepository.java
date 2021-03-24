package com.jarvis.adminservice.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseRepository<BaseEntity> extends JpaRepository<BaseEntity, Integer>, JpaSpecificationExecutor<BaseEntity> {

    Optional<BaseEntity> findById(@Param("id") long id);
}
