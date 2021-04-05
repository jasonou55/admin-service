package com.jarvis.adminservice.request;

import org.springframework.stereotype.Component;

@Component
public class OrganizationRequest extends GenericRequestImpl {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
