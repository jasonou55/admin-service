package com.jarvis.adminservice.service;

import com.jarvis.adminservice.entity.Organization;
import com.jarvis.adminservice.repository.GenericRepository;
import com.jarvis.adminservice.repository.OrganizationRepository;
import com.jarvis.adminservice.request.OrganizationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService extends GenericService<Organization, OrganizationRequest> {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization entity() { return new Organization(); }

    @Override
    public GenericRepository<Organization> repository() { return organizationRepository; }

    public boolean hasExist(OrganizationRequest organizationRequest) {
        return organizationRepository.findByName(organizationRequest.getName()).isPresent();
    }
}
