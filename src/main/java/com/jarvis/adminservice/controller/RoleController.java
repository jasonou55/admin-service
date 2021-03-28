package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.constant.PathParameterConstants;
import com.jarvis.adminservice.entity.Role;
import com.jarvis.adminservice.request.RoleRequest;
import com.jarvis.adminservice.response.RoleResponse;
import com.jarvis.adminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
public class RoleController extends GenericController<Role, RoleRequest, RoleResponse, RoleService> {

    @Autowired RoleService roleService;

    @Autowired HttpServletRequest request;

    @Override
    public Role getEntity() {
        return new Role();
    }

    @Override
    public RoleResponse getResponse() {
        return new RoleResponse();
    }

    @Override
    public RoleService getService() {
        return roleService;
    }

    @GetMapping("/roles")
    @ResponseBody
    public ResponseEntity<RoleResponse> get() {

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

    @PostMapping("/roles")
    @ResponseBody
    public ResponseEntity<RoleResponse> create(@Validated @RequestBody RoleRequest roleRequest) {
        return this.doCreate(roleRequest);
    }

    @PutMapping("/roles")
    @ResponseBody
    public ResponseEntity<RoleResponse> update(@Validated @RequestBody RoleRequest roleRequest) {
        return this.doUpdate(roleRequest);
    }

    @DeleteMapping("/roles/{identifier}")
    @ResponseBody
    public ResponseEntity<RoleResponse> disable(@PathVariable String identifier) { return this.doDisable(identifier); }

    @DeleteMapping("/roles")
    @ResponseBody
    public ResponseEntity<RoleResponse> disableAll(@RequestBody List<String> identifiers) {
        return this.doDisableAll(identifiers);
    }
}
