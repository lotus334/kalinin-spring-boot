package com.securityUsersDB;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class SecurityUsersDBController {

    @GetMapping("/")
    public ResponseEntity<String> getGreetingsV2() {
        return ResponseEntity.ok().body("Hello from server!");
    }

    @GetMapping("/api/v3/greetings")
    public ResponseEntity<Map<String, String>> getGreetingsV3(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("greeting", "Hello from v2, %s!".formatted(userDetails.getUsername())));
    }

    @GetMapping("/api/v5/greetings")
    public ResponseEntity<Map<String, String>> getGreetingsV5(Principal principal) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("greeting", "Hello from v2, %s!".formatted(principal.getName())));
    }

}
