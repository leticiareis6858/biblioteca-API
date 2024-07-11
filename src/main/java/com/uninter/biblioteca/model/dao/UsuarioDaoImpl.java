package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// classe que implementa o usuarioDao
@Repository
@Transactional
public class UsuarioDaoImpl extends AbstractDao<Usuario, Long> implements UsuarioDao {

    @Override
    public Usuario save(Usuario usuario) {
        super.save(usuario);
        return usuario;
    }

    @Override
    public void update(Usuario usuario) {
        super.update(usuario);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public Usuario findById(Long id) {
        return super.findById(id);
    }

    @Override
    public java.util.List<Usuario> findAll() {
        return super.findAll();
    }
}