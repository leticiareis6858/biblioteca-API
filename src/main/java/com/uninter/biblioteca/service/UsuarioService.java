package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.entity.Usuario;
import com.uninter.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// classe de serviço para usuario
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // método para criar um usuario
    public Usuario criarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    // método para adicionar um usuario
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
            usuarioExistente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        if (usuario.getCargo() != null) {
            usuarioExistente.setCargo(usuario.getCargo());
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // método para excluir um usuario
    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // método para obter um usuario pelo seu id
    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // método para obter todos os usuarios
    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }
}
