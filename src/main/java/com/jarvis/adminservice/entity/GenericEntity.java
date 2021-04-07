package com.jarvis.adminservice.entity;

import java.io.Serializable;

public interface GenericEntity extends Serializable {

    long getId();
    void setId(final long id);

    long getCreated();
    void setCreated(final long created);

    long getUpdated();
    void setUpdated(final long updated);

    boolean isEnabled();
    void setEnabled(final boolean enabled);

    String getIdentifier();
    void setIdentifier();
}
