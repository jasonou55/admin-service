package com.jarvis.adminservice.response;

import org.springframework.stereotype.Component;

@Component
public class GenericResponseImpl implements GenericResponse {

    private boolean success;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    @Override
    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    @Override
    public void setData(final Object data) {
        this.data = data;
    }
}
