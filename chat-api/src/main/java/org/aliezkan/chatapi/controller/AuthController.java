package org.aliezkan.chatapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aliezkan.chatapi.dto.request.AuthenticationRequest;
import org.aliezkan.chatapi.dto.response.AuthenticationResponse;
import org.aliezkan.chatapi.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void register(@RequestBody @Valid AuthenticationRequest request) {
        authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Valid AuthenticationRequest request){
        return authenticationService.login(request);
    }

    @GetMapping("/whoami")
    public String getUserName(Authentication authentication) {
        return authentication.getName();
    }
}