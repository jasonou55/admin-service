package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.entity.GenericEntity;
import com.jarvis.adminservice.entity.GenericEntityImpl;
import com.jarvis.adminservice.request.GenericRequest;
import com.jarvis.adminservice.response.GenericResponse;
import com.jarvis.adminservice.response.GenericResponseImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GenericController {

    protected GenericEntity convertEntityFromRequest(GenericRequest genericRequest) {
        GenericEntity genericEntity = new GenericEntityImpl();
        BeanUtils.copyProperties(genericRequest, genericEntity);
        return genericEntity;
    }

    protected GenericResponse convertEntityToResponse(GenericEntity genericEntity) {
        GenericResponse genericResponse = new GenericResponseImpl();
        BeanUtils.copyProperties(genericEntity, genericResponse);
        return genericResponse;
    }

    protected ResponseEntity<GenericResponse> generateResponseEntity(GenericResponse genericResponse, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(genericResponse);
    }
}
