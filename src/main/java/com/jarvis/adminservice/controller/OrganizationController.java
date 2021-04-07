package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.entity.Organization;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.request.OrganizationRequest;
import com.jarvis.adminservice.response.OrganizationResponse;
import com.jarvis.adminservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/management")
public class OrganizationController extends GenericController<Organization, OrganizationRequest, OrganizationResponse, OrganizationService> {

    @Autowired OrganizationService organizationService;

    @Override
    public Organization getEntity() { return new Organization(); }

    @Override
    public OrganizationService getService() { return organizationService; }

    @Override
    public OrganizationResponse getResponse() { return new OrganizationResponse(); }

    @GetMapping("/organizations")
    @ResponseBody
    public ResponseEntity<OrganizationResponse> get() {
        return this.doGet();
    }

    @PostMapping("/organizations")
    @ResponseBody
    public ResponseEntity<OrganizationResponse> create(@Validated @RequestBody OrganizationRequest organizationRequest) {
        if (organizationService.hasExist(organizationRequest)) {
            throw new ServiceException(ErrorCode.DUPLICATED_UNIQUE_FIELD, "The organization name already exist.", HttpStatus.BAD_REQUEST);
        }
        return this.doCreate(organizationRequest);
    }

    @PutMapping("/organizations")
    @ResponseBody
    public ResponseEntity<OrganizationResponse> update(@Validated @RequestBody OrganizationRequest organizationRequest) {
        return this.doUpdate(organizationRequest);
    }

    @DeleteMapping("/organizations")
    @ResponseBody
    public ResponseEntity<OrganizationResponse> disableAll(@RequestBody List<String> identifiers) {
        return this.doDisableAll(identifiers);
    }

}
