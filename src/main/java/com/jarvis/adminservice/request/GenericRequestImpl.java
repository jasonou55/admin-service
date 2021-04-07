package com.jarvis.adminservice.request;

import org.springframework.stereotype.Component;

@Component
public class GenericRequestImpl implements GenericRequest {

    private String identifier;
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
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enable) {
        this.enabled = enable;
    }
}
