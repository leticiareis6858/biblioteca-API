package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Usuario;

import java.util.List;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// interface usuarioDao
public interface UsuarioDao {
    Usuario save(Usuario usuario);
    void update(Usuario usuario);
    void delete(Long id);
    Usuario findById(Long id);
    List<Usuario> findAll();
}
