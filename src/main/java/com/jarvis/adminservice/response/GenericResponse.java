package com.jarvis.adminservice.response;

public interface GenericResponse {

    void setSuccess(final boolean success);
    void setData(final Object data);
}
