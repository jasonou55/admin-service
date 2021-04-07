package com.jarvis.adminservice.repository;

import com.jarvis.adminservice.entity.Organization;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends GenericRepository<Organization> {

    Optional<Organization> findByName(String name);
}
