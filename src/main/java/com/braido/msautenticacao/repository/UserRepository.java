package com.braido.msautenticacao.repository;

import com.braido.msautenticacao.entities.UsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsuarioLogin, Long> {
}
