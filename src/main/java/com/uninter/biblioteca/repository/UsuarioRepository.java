package com.uninter.biblioteca.repository;

import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// repositorio para a entidade usuario
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
