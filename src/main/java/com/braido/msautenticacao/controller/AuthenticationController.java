package com.braido.msautenticacao.controller;

import com.braido.msautenticacao.dto.request.RefreshTokenRequest;
import com.braido.msautenticacao.dto.request.SignUpRequest;
import com.braido.msautenticacao.dto.request.SigninRequest;
import com.braido.msautenticacao.dto.response.JwtAuthenticationResponse;
import com.braido.msautenticacao.entities.UsuarioLogin;
import com.braido.msautenticacao.services.AuthenticationSerice;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationSerice authenticationSerice;

    @PostMapping("/signup")
    public ResponseEntity<UsuarioLogin> signup(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authenticationSerice.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request){
        log.info("[START] AuthenticationController - signin ({})", request.getEmail());
        return ResponseEntity.ok(authenticationSerice.signin(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(authenticationSerice.refreshToken(request));
    }

}
