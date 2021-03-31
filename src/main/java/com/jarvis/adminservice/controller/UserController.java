package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.entity.User;
import com.jarvis.adminservice.request.UserRequest;
import com.jarvis.adminservice.response.UserResponse;
import com.jarvis.adminservice.service.UserService;
import com.jarvis.adminservice.util.DesUtil;
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

import java.util.List;

@RestController
public class UserController extends GenericController<User, UserRequest, UserResponse, UserService> {

    @Autowired UserService userService;

    @Override public User getEntity() {
        return new User();
    }

    @Override public UserService getService() {
        return userService;
    }

    @Override public UserResponse getResponse() {
        return new UserResponse();
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<UserResponse> get() {
        return this.doGet();
    }

    @GetMapping("/users/{identifier}")
    @ResponseBody
    public ResponseEntity<UserResponse> getOne(@PathVariable String identifier) { return this.doGet(identifier); }

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest userRequest) {

        String password = userRequest.getPassword();
        userRequest.setPassword(DesUtil.getDecryptString(password));
        return this.doCreate(userRequest);
    }

    @PutMapping("/users")
    @ResponseBody
    public ResponseEntity<UserResponse> update(@Validated @RequestBody UserRequest userRequest) {
        return this.doUpdate(userRequest, "password");
    }

    @DeleteMapping("/users/{identifier}")
    @ResponseBody
    public ResponseEntity<UserResponse> disable(@PathVariable String identifier) { return this.doDisable(identifier); }

    @DeleteMapping("/users")
    @ResponseBody
    public ResponseEntity<UserResponse> disableAll(@RequestBody List<String> identifiers) {
        return this.doDisableAll(identifiers);
    }

    @PutMapping("/users/{identifier}/roles")
    @ResponseBody
    public ResponseEntity<UserResponse> assignRoles(@PathVariable String identifier, @RequestBody List<String> roleIdentifiers) {
        userService.assignRolesToUser(identifier, roleIdentifiers);
        return this.generateResponseEntity(this.convertEntityToResponse(null), HttpStatus.OK);
    }
}
