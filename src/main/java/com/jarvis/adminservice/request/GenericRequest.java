package com.jarvis.adminservice.request;

public interface GenericRequest {

    String getIdentifier();
    void setIdentifier(final String identifier);
    boolean isEnabled();
    void setEnabled(final boolean enable);
}
