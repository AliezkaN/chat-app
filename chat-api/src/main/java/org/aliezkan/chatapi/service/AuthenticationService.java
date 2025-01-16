package org.aliezkan.chatapi.service;

import lombok.RequiredArgsConstructor;
import org.aliezkan.chatapi.dto.request.AuthenticationRequest;
import org.aliezkan.chatapi.dto.response.AuthenticationResponse;
import org.aliezkan.chatapi.exceptions.UserAlreadyExistsException;
import org.aliezkan.chatapi.persistance.UserEntity;
import org.aliezkan.chatapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(AuthenticationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new UserAlreadyExistsException("User with username %s already exists!".formatted(request.getUsername()));

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .build();
        userRepository.save(userEntity);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Map<String, Object> claims = new HashMap<>();
        UserEntity user = (UserEntity) auth.getPrincipal();
        claims.put("username", user.getUsername());

        String jwtToken = jwtService.generateToken(claims, (UserEntity) auth.getPrincipal());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
