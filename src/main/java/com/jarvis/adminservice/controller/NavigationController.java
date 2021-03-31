package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.entity.Navigation;
import com.jarvis.adminservice.request.NavigationRequest;
import com.jarvis.adminservice.request.RoleRequest;
import com.jarvis.adminservice.response.NavigationResponse;
import com.jarvis.adminservice.response.RoleResponse;
import com.jarvis.adminservice.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@RestController
public class NavigationController extends GenericController<Navigation, NavigationRequest, NavigationResponse, NavigationService> {

    @Autowired
    NavigationService navigationService;

    @Override public Navigation getEntity() {
        return new Navigation();
    }

    @Override public NavigationService getService() {
        return navigationService;
    }

    @Override public NavigationResponse getResponse() {
        return new NavigationResponse();
    }

    @GetMapping("/navigations")
    @ResponseBody
    public ResponseEntity<NavigationResponse> get() {
        return this.doGet();
    }

    @PostMapping("/navigations")
    @ResponseBody
    public ResponseEntity<NavigationResponse> create(@Validated @RequestBody NavigationRequest navigationRequest) {
        return this.doCreate(navigationRequest);
    }

    @PutMapping("/navigations")
    @ResponseBody
    public ResponseEntity<NavigationResponse> update(@Validated @RequestBody NavigationRequest navigationRequest) {
        return this.doUpdate(navigationRequest);
    }

    @DeleteMapping("/navigations/{identifier}")
    @ResponseBody
    public ResponseEntity<NavigationResponse> disable(@PathVariable String identifier) { return this.doDisable(identifier); }

    @DeleteMapping("/navigations")
    @ResponseBody
    public ResponseEntity<NavigationResponse> disableAll(@RequestBody List<String> identifiers) {
        return this.doDisableAll(identifiers);
    }
}
