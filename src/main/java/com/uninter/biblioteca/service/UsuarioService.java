package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.dao.UsuarioDaoImpl;
import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.stereotype.Service;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

import java.util.List;

// classe de serviço para usuario
@Service
public class UsuarioService {

   private final UsuarioDaoImpl usuarioDao;

   public UsuarioService(UsuarioDaoImpl usuarioDao) {
       this.usuarioDao = usuarioDao;
   }

    // método para criar um usuario
    public Usuario criarUsuario(Usuario usuario) {
       Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setCargo(usuario.getCargo());
        return usuarioDao.save(novoUsuario);
    }

    // método para adicionar um usuario
    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioDao.findById(id);
        if(usuarioExistente == null) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }

        if (usuario.getCargo() != null) {
            throw new IllegalArgumentException("Não é permitido mudar o cargo do usuário.");
        }

        if (usuario.getNome() != null) {
            usuarioExistente.setNome(usuario.getNome());
        }

        if (usuario.getEmail() != null) {
            usuarioExistente.setEmail(usuario.getEmail());
        }

        if (usuario.getCargo() != null) {
            usuarioExistente.setCargo(usuario.getCargo());
        }

        usuarioDao.update(usuarioExistente);
        return usuarioExistente;
    }

    // método para excluir um usuario
    public void removerUsuario(Long id) {
        usuarioDao.delete(id);
    }

    // método para obter um usuario pelo seu id
    public Usuario obterUsuarioPorId(Long id) {
       Usuario usuarioExistente=usuarioDao.findById(id);
       if(usuarioExistente==null) {
           throw new RuntimeException("Usuário não encontrado com o ID: " + id);
       }
       return usuarioExistente;
    }

    // método para obter todos os usuarios
    public List<Usuario> obterTodosUsuarios() {
        List<Usuario> usuarios=usuarioDao.findAll();
        if(usuarios.isEmpty()){
            throw new RuntimeException("Nenhum usuário encontrado!");
        }
        return usuarios;
    }
}
