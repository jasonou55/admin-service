package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.constant.PathParameterConstants;
import com.jarvis.adminservice.entity.GenericEntity;
import com.jarvis.adminservice.request.GenericRequest;
import com.jarvis.adminservice.response.GenericResponse;
import com.jarvis.adminservice.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

public abstract class GenericController<T extends GenericEntity, S extends GenericRequest, W extends GenericResponse,
        U extends GenericService<T, S>> {

    public abstract T getEntity();
    public abstract U getService();
    public abstract W getResponse();

    @Autowired HttpServletRequest request;

    protected W convertEntityToResponse(Object object) {
        W response = this.getResponse();
        response.setSuccess(true);
        response.setData(object);
        return response;
    }

    protected ResponseEntity<W> generateResponseEntity(W response, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(response);
    }

    protected ResponseEntity<W> doGet(String identifier) {
        return this.generateResponseEntity(this.convertEntityToResponse(this.getService().getOne(identifier)), HttpStatus.OK);
    }

    protected ResponseEntity<W> doGet() {
        String state = request.getParameter(PathParameterConstants.STATE);
        if (state == null) {
            return this.doGetAll();
        }
        if (state.equalsIgnoreCase(PathParameterConstants.ENABLED)) {
            return this.doGetAllEnabled();
        }
        if (state.equalsIgnoreCase(PathParameterConstants.DISABLED)) {
            return this.doGetAllDisabled();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    protected ResponseEntity<W> doGetAll() {
        return this.generateResponseEntity(this.convertEntityToResponse(this.getService().getAll()), HttpStatus.OK);
    }

    protected ResponseEntity<W> doGetAllEnabled() {
        return this.generateResponseEntity(this.convertEntityToResponse(this.getService().getAllEnabled()), HttpStatus.OK);
    }

    protected ResponseEntity<W> doGetAllDisabled() {
        return this.generateResponseEntity(this.convertEntityToResponse(this.getService().getAllDisabled()), HttpStatus.OK);
    }

    protected ResponseEntity<W> doCreate(S request) {
        T savedEntity = this.getService().create(request);
        return this.generateResponseEntity(this.convertEntityToResponse(savedEntity), HttpStatus.CREATED);
    }

    protected ResponseEntity<W> doUpdate(S request, String... ignoreProperties) {
        T entity = this.getService().update(request, ignoreProperties);
        return this.generateResponseEntity(this.convertEntityToResponse(entity), HttpStatus.OK);
    }

    protected ResponseEntity<W> doDisable(String identifier) {
        this.getService().disable(identifier);
        return this.generateResponseEntity(this.convertEntityToResponse(null), HttpStatus.NO_CONTENT);
    }

    protected ResponseEntity<W> doDisableAll(List<String> identifiers) {
        this.getService().disableAll(identifiers);
        return this.generateResponseEntity(this.convertEntityToResponse(null), HttpStatus.NO_CONTENT);
    }
}
