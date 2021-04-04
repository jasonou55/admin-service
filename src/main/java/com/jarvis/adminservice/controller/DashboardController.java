package com.jarvis.adminservice.controller;

import com.jarvis.adminservice.response.DashboardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class DashboardController {

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboardData() {
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setSuccess(true);
        dashboardResponse.setData("This is dashboard data.");

        return ResponseEntity.ok(dashboardResponse);
    }
}
