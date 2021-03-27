package com.jarvis.adminservice.request;

import org.springframework.stereotype.Component;

@Component
public class GenericRequestImpl implements GenericRequest {

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
        if (this.created == null) {
            this.setCreated(System.currentTimeMillis());
        }
        return created;
    }

    public void setCreated(final Long created) {
        this.created = created;
    }

    public Long getCreatedBy() {
        if (this.createdBy == null) {
            this.setCreatedBy(0L);
        }
        return createdBy;
    }

    public void setCreatedBy(final Long createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getActive() {
        if (this.active == null) {
            this.setActive(true);
        }
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }
}
