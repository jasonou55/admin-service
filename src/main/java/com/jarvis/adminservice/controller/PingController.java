package com.jarvis.adminservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    @ResponseBody
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok().body("Pong");
    }
}
