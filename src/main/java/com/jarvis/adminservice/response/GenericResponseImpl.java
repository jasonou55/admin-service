package com.jarvis.adminservice.response;

import com.jarvis.adminservice.entity.GenericEntityImpl;
import org.springframework.stereotype.Component;

@Component
public class GenericResponseImpl implements GenericResponse {

    private boolean success;
    private GenericEntityImpl genericEntityImpl;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public GenericEntityImpl getBaseEntity() {
        return genericEntityImpl;
    }

    public void setBaseEntity(final GenericEntityImpl genericEntityImpl) {
        this.genericEntityImpl = genericEntityImpl;
    }
}
