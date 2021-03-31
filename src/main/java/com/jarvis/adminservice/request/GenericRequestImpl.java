package com.jarvis.adminservice.request;

import org.springframework.stereotype.Component;

@Component
public class GenericRequestImpl implements GenericRequest {

    private String identifier;
    private long created;
    private long createdBy;
    private boolean enabled;

    public GenericRequestImpl() {
        this.enabled = true;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    @Override
    public long getCreated() {
        if (this.created == 0) {
            this.setCreated(System.currentTimeMillis());
        }
        return created;
    }

    @Override
    public void setCreated(final long created) {
        this.created = created;
    }

    @Override
    public long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(final long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enable) {
        this.enabled = enable;
    }
}
