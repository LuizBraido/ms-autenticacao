package com.braido.msautenticacao.services;

import com.braido.msautenticacao.dto.request.RefreshTokenRequest;
import com.braido.msautenticacao.dto.request.SignUpRequest;
import com.braido.msautenticacao.dto.request.SigninRequest;
import com.braido.msautenticacao.dto.response.JwtAuthenticationResponse;
import com.braido.msautenticacao.entities.UsuarioLogin;

public interface AuthenticationSerice {

    UsuarioLogin signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SigninRequest request);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest request);

}
