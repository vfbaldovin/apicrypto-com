package com.web.apicrypto.webapp.controller;

import com.web.apicrypto.webapp.model.dto.ApiResponse;
import com.web.apicrypto.webapp.model.dto.ChangePasswordRequest;
import com.web.apicrypto.webapp.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/crypto/names")
    public ResponseEntity<List<String>> getCryptoNames(@RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity<>(dashboardService.getCryptoNames(name), OK);
    }
}
