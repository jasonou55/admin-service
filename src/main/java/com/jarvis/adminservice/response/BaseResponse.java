package com.jarvis.adminservice.response;

import org.springframework.stereotype.Component;

@Component
public class BaseResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }
}
