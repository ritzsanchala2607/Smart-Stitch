package com.smart_stitch.backend.controller;

import com.smart_stitch.backend.dto.AuthRequest;
import com.smart_stitch.backend.service.AuthService;
import com.smart_stitch.backend.util.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final AuthService authService;

    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody AuthRequest request) {
        try {
            authService.registerUser(request, "ROLE_CUSTOMER");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Customer registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register/worker")
    public ResponseEntity<?> registerWorker(@RequestBody AuthRequest request) {
        try {
            authService.registerUser(request, "ROLE_WORKER");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Worker registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register/owner")
    public ResponseEntity<?> registerOwner(@RequestBody AuthRequest request) {
        try {
            authService.registerUser(request, "ROLE_SHOP_OWNER");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Shop Owner registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            String token = jwtHelper.generateToken(request.getUsername());

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }
    }
}
