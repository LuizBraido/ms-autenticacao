package com.braido.msautenticacao;

import com.braido.msautenticacao.entities.UsuarioLogin;
import com.braido.msautenticacao.enums.Role;
import com.braido.msautenticacao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MsAutenticacaoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MsAutenticacaoApplication.class, args);
    }

    public void run(String... args){
        UsuarioLogin adminAccount = userRepository.findByRole(Role.ADMIN);
        if (null == adminAccount){
            UsuarioLogin usuarioLogin = new UsuarioLogin();

            usuarioLogin.setEmail("lpaulobn@outlook.com");
            usuarioLogin.setFirstName("Luiz");
            usuarioLogin.setSecondName("Braido");
            usuarioLogin.setRole(Role.ADMIN);
            usuarioLogin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(usuarioLogin);
        }
    }

}
