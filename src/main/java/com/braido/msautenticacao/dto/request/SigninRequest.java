package com.braido.msautenticacao.dto.request;

import lombok.Data;

@Data
public class SigninRequest {

    private String email;
    private String password;

}
