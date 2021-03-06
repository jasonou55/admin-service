package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.constant.SettingConstants;
import com.jarvis.adminservice.entity.Role;
import com.jarvis.adminservice.request.RoleRequest;
import com.jarvis.adminservice.response.RoleResponse;
import com.jarvis.adminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/management")
public class RoleController extends GenericController<Role, RoleRequest, RoleResponse, RoleService> {

    @Autowired RoleService roleService;

    @Override
    public Role getEntity() {
        return new Role();
    }

    @Override
    public RoleService getService() {
        return roleService;
    }

    @Override
    public RoleResponse getResponse() {
        return new RoleResponse();
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ROLE_root')")
    @ResponseBody
    public ResponseEntity<RoleResponse> get() {
        return this.doGet();
    }

    @PostMapping("/roles")
    @PreAuthorize("hasRole('ROLE_root')")
    @ResponseBody
    public ResponseEntity<RoleResponse> create(@Validated @RequestBody RoleRequest roleRequest) {
        if (roleRequest.getName() != null && !roleRequest.getName().startsWith(SettingConstants.ROLE_PREFIX)) {
            roleRequest.setName(SettingConstants.ROLE_PREFIX + roleRequest.getName());
        }
        return this.doCreate(roleRequest);
    }

    @PutMapping("/roles")
    @PreAuthorize("hasRole('ROLE_root')")
    @ResponseBody
    public ResponseEntity<RoleResponse> update(@Validated @RequestBody RoleRequest roleRequest) {
        if (roleRequest.getName() != null && !roleRequest.getName().startsWith(SettingConstants.ROLE_PREFIX)) {
            roleRequest.setName(SettingConstants.ROLE_PREFIX + roleRequest.getName());
        }
        return this.doUpdate(roleRequest);
    }

    @DeleteMapping("/roles/{identifier}")
    @PreAuthorize("hasRole('ROLE_root')")
    @ResponseBody
    public ResponseEntity<RoleResponse> disable(@PathVariable String identifier) { return this.doDisable(identifier); }

    @DeleteMapping("/roles")
    @PreAuthorize("hasRole('ROLE_root')")
    @ResponseBody
    public ResponseEntity<RoleResponse> disableAll(@RequestBody List<String> identifiers) {
        return this.doDisableAll(identifiers);
    }
}
