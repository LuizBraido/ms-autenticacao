package com.braido.msautenticacao.dto.response;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;

}
