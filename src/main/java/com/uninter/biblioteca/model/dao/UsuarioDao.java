package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UsuarioDao {
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(Long id);
    Usuario findById(Long id);
    List<Usuario> findAll();
}
