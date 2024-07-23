package com.deutscheBahn.controller;

import com.deutscheBahn.payload.request.LoginRequest;
import com.deutscheBahn.payload.response.AuthResponse;
import com.deutscheBahn.security.jwt.JwtUtils;
import com.deutscheBahn.security.service.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    public final JwtUtils jwtUtils;
    public final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest){

        String username= loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);

       UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

       Set<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        Optional<String> role = roles.stream().findFirst();

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();
        authResponse.username(userDetails.getUsername());
        authResponse.token(token);
        authResponse.firstName(userDetails.getName());
        authResponse.lastName(userDetails.getLastName());
        if(role.isPresent()){
            authResponse.role(role.get());
        }

        return ResponseEntity.ok(authResponse.build());
    }


}
