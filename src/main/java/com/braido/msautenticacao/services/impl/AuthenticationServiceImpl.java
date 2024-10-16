package com.braido.msautenticacao.services.impl;

import com.braido.msautenticacao.dto.request.RefreshTokenRequest;
import com.braido.msautenticacao.dto.request.SignUpRequest;
import com.braido.msautenticacao.dto.request.SigninRequest;
import com.braido.msautenticacao.dto.response.JwtAuthenticationResponse;
import com.braido.msautenticacao.entities.UsuarioLogin;
import com.braido.msautenticacao.enums.Role;
import com.braido.msautenticacao.repository.UserRepository;
import com.braido.msautenticacao.services.AuthenticationSerice;
import com.braido.msautenticacao.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationSerice {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UsuarioLogin signup(SignUpRequest signUpRequest){
        UsuarioLogin usuario = new UsuarioLogin();

        usuario.setEmail(signUpRequest.getEmail());
        usuario.setFirstName(signUpRequest.getFirstName());
        usuario.setSecondName(signUpRequest.getLastName());
        usuario.setRole(Role.USER);
        usuario.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(usuario);
    }

    public JwtAuthenticationResponse signin(SigninRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new IllegalArgumentException("Dados inválidos"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();

        response.setToken(jwt);
        response.setRefreshToken(refreshToken);

        return response;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request){
        String userEmail = jwtService.extractUserName(request.getToken());
        UsuarioLogin usuario = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(request.getToken(), usuario)){
           var jwt = jwtService.generateToken(usuario);

            JwtAuthenticationResponse response = new JwtAuthenticationResponse();

            response.setToken(jwt);
            response.setRefreshToken(request.getToken());

            return response;
        }
        return null;
    }

}
