package com.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Autowired
    BackendAdapter backendAdapter;

    @GetMapping("/retryable-operation")
    public ResponseEntity<String> validateSpringRetryCapability() {
        String apiResponse = backendAdapter.getBackendResponse(null, null);
        return ResponseEntity.ok().body(apiResponse);
    }
}