package com.jarvis.adminservice.request;

public interface GenericRequest {

    String getIdentifier();
    void setIdentifier(final String identifier);
    long getCreated();
    void setCreated(final long created);
    long getCreatedBy();
    void setCreatedBy(final long createdBy);
    boolean isEnabled();
    void setEnabled(final boolean enable);
}
