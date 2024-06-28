package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioDao extends Dao<Usuario, Long> {
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    void delete(Long id);
    Usuario findById(Long id);
    List<Usuario> findAll();
}
