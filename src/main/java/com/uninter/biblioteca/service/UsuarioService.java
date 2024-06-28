package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.dao.UsuarioDao;
import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDao dao;

    public UsuarioService(@Qualifier("usuarioDao") UsuarioDao dao) {
        this.dao = dao;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return dao.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = dao.findById(id);
        if (usuarioExistente != null) {
            if (usuario.getCargo() != null) {
                throw new IllegalArgumentException("Não é permitido mudar o cargo do usuário.");
            }

            if (usuario.getNome() != null) {
                usuarioExistente.setNome(usuario.getNome());
            }
            if (usuario.getEmail() != null) {
                usuarioExistente.setEmail(usuario.getEmail());
            }
            if (usuario.getSenha() != null) {
                usuarioExistente.setSenha(passwordEncoder.encode(usuario.getSenha()));
            }
            if (usuario.getCargo() != null) {
                usuarioExistente.setCargo(usuario.getCargo());
            }

            return dao.save(usuarioExistente);

        } else {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
    }


    public void removerUsuario(Long id) {
        dao.delete(id);
    }

    public Usuario obterUsuarioPorId(Long id) {
        Usuario usuarioExistente = dao.findById(id);
        if (usuarioExistente != null) {
            return usuarioExistente;
        } else {
            throw new RuntimeException(("Usuário não encontrado com o ID: " + id));
        }
    }

    public List<Usuario> obterTodosUsuarios() {
        return dao.findAll();
    }
}
