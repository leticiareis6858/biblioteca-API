package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.entity.Usuario;
import com.uninter.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

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
            usuarioExistente.setSenha(usuario.getSenha());
        }
        if (usuario.getCargo() != null) {
            usuarioExistente.setCargo(usuario.getCargo());
        }

        return usuarioRepository.save(usuarioExistente);
    }


    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }
}
