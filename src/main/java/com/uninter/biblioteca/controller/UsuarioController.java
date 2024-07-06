package com.uninter.biblioteca.controller;

import com.uninter.biblioteca.controller.dto.UsuarioDTO;
import com.uninter.biblioteca.model.entity.Usuario;
import com.uninter.biblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

import static com.uninter.biblioteca.swagger.ExemplosRequestBody.*;
import static com.uninter.biblioteca.swagger.ExemplosResponses.*;

// controller para usuarios
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // rota para criar um usuário
    @PostMapping("/criar")
    // anotações do swagger
    @Operation(description = "Cria um usuário")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. É necessário informar todos os atributos.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarUsuarioBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o usuário criado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarUsuarioResponse)))
    // rota em si
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.ok(novoUsuario);
    }

    // rota para atualizar um usuário pelo seu id
    @PatchMapping("/atualizar/{id}")
    // anotações do swagger
    @Operation(description = "Atualiza um usuário")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. É possível atualizar quantos e quaisquer atributos quiser.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarUsuarioBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o usuário atualizado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarUsuarioResponse)))
    // rota em si
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.atualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(usuario);
    }

    // rota para excluir um usuário pelo seu id
    @DeleteMapping("/excluir/{id}")
    // anotações do swagger
    @Operation(description = "Remove um usuário.")
    @ApiResponse(responseCode = "200", description = "Retorna uma mensagem de sucesso",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Usuário excluido com sucesso!")))
    //rota em si
    public ResponseEntity<String> removerUsuario(@PathVariable Long id) {
        usuarioService.removerUsuario(id);
        return ResponseEntity.ok("Usuário excluido com sucesso!");
    }

    // rota para obter um usuário pelo seu id
    @GetMapping("/{id}")
    // anotações do swagger
    @Operation(description = "Obtém os dados de um usuário por seu ID.")
    @ApiResponse(responseCode = "200", description = "Retorna o usuário buscado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterUsuarioPorIdResponse)))
    // rota em si
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // rota para obter todos os usuários
    @GetMapping
    // anotações do swagger
    @Operation(description = "Obtém todos os usuários.")
    @ApiResponse(responseCode = "200", description = "Retorna todos os usuários",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterTodosUsuariosResponse)))
    // rota em si
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.obterTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
