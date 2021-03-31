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

    @GetMapping("/all/1")
    @ResponseBody
    public ResponseEntity<String> all() {
        return ResponseEntity.ok().body("All");
    }

    @GetMapping("/admin/1")
    @ResponseBody
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok().body("Admin");
    }

    @GetMapping("/root/1")
    @ResponseBody
    public ResponseEntity<String> root() {
        return ResponseEntity.ok().body("Root");
    }
}
