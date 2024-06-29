package com.uninter.biblioteca.controller;

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

import static com.uninter.biblioteca.swagger.ExemplosRequestBody.*;
import static com.uninter.biblioteca.swagger.ExemplosResponses.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")@Operation(description = "Cria um usuário")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. É necessário informar todos os atributos.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarUsuarioBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o usuário criado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarUsuarioResponse)))
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @PatchMapping("/atualizar/{id}")
    @Operation(description = "Atualiza um usuário")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. É possível atualizar quantos e quaisquer atributos quiser.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarUsuarioBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o usuário atualizado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarUsuarioResponse)))
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioService.atualizarUsuario(id, usuarioAtualizado);
        return ResponseEntity.ok(usuario);
    }


    @DeleteMapping("/excluir/{id}")
    @Operation(description = "Remove um usuário.")
    @ApiResponse(responseCode = "200", description = "Retorna uma mensagem de sucesso",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Usuário excluido com sucesso!")))
    public ResponseEntity<String> removerUsuario(@PathVariable Long id) {
        usuarioService.removerUsuario(id);
        return ResponseEntity.ok("Usuário excluido com sucesso!");
    }

    @GetMapping("/{id}")
    @Operation(description = "Obtém os dados de um usuário por seu ID.")
    @ApiResponse(responseCode = "200", description = "Retorna o usuário buscado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterUsuarioPorIdResponse)))
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(description = "Obtém todos os usuários.")
    @ApiResponse(responseCode = "200", description = "Retorna todos os usuários",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterTodosUsuariosResponse)))
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.obterTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
