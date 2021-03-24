package com.jarvis.adminservice.request;

import org.springframework.stereotype.Component;

@Component
public class BaseRequest {

    private String identifier;
    private Long created;
    private Long createdBy;
    private Boolean active;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(final Long created) {
        this.created = created;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final Long createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }
}
