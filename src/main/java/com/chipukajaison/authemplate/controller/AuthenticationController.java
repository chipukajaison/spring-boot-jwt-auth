package com.chipukajaison.authemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chipukajaison.authemplate.dto.request.LoginRequest;
import com.chipukajaison.authemplate.dto.request.RegisterRequest;
import com.chipukajaison.authemplate.model.User;
import com.chipukajaison.authemplate.security.TokenProvider;
import com.chipukajaison.authemplate.service.AuthenticationService;

/**
 * @author Jaison.Chipuka on 6/6/2024
 * @project Auth Template
 * @email chipukajaison@gmail.com
 */
@RestController
@RequestMapping("/api/1.0.0/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final TokenProvider tokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, AuthenticationService authenticationService, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> authenticate(@RequestBody RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(accessToken);
    }
}
