package com.uninter.biblioteca.repository;

import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// repositorio para a entidade usuario
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
