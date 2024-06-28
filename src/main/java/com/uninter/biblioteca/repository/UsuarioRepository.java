package com.uninter.biblioteca.repository;

import com.uninter.biblioteca.model.dao.AbstractDao;
import com.uninter.biblioteca.model.dao.UsuarioDao;
import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository extends AbstractDao<Usuario, Long> implements UsuarioDao {
}
