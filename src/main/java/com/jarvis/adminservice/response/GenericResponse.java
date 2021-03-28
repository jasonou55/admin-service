package com.jarvis.adminservice.response;

import com.jarvis.adminservice.entity.GenericEntity;

public interface GenericResponse {

    void setSuccess(final boolean success);
    void setData(final Object data);
}
