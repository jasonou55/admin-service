package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.repository.UserRepository;
import com.jarvis.adminservice.request.LoginRequest;
import com.jarvis.adminservice.response.LoginResponse;
import com.jarvis.adminservice.response.UserResponse;
import com.jarvis.adminservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(loginService.login(loginRequest));
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<LoginResponse> logout() {

        return ResponseEntity.ok(new LoginResponse());
    }
}
