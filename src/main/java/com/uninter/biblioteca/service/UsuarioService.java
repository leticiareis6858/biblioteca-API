package com.uninter.biblioteca.service;

import com.uninter.biblioteca.controller.dto.UsuarioDTO;
import com.uninter.biblioteca.model.entity.Usuario;
import com.uninter.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// classe de serviço para usuario
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // método para criar um usuario
    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setCargo(usuarioDTO.getCargo());
        return usuarioRepository.save(usuario);
    }

    // método para adicionar um usuario
    public Usuario atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        if (usuarioDTO.getCargo() != null) {
            throw new IllegalArgumentException("Não é permitido mudar o cargo do usuário.");
        }

        if (usuarioDTO.getNome() != null) {
            usuarioExistente.setNome(usuarioDTO.getNome());
        }

        if (usuarioDTO.getEmail() != null) {
            usuarioExistente.setEmail(usuarioDTO.getEmail());
        }

        if (usuarioDTO.getCargo() != null) {
            usuarioExistente.setCargo(usuarioDTO.getCargo());
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // método para excluir um usuario
    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // método para obter um usuario pelo seu id
    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    // método para obter todos os usuarios
    public List<Usuario> obterTodosUsuarios() {
        List<Usuario> usuarios=usuarioRepository.findAll();
        if(usuarios.isEmpty()){
            throw new RuntimeException("Nenhum usuário encontrado!");
        }
        return usuarios;
    }
}
