package com.jarvis.adminservice.repository;

import com.jarvis.adminservice.entity.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T> {

    List<T> findAllByEnabled(boolean enabled);
}
