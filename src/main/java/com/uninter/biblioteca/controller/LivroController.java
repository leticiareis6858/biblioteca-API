package com.uninter.biblioteca.controller;

import com.uninter.biblioteca.model.entity.Livro;
import com.uninter.biblioteca.service.LivroService;
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

// controller de livros
@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    // rota para criar um livro
    @PostMapping("/adicionar")
    // anotações do swagger
    @Operation(description = "Cria um livro")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. Não é necessário informar a disponibilidade.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarLivroBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o livro criado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarLivroResponse)))
    // rota em si
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.adicionarLivro(livro);
        return ResponseEntity.ok(novoLivro);
    }

    // rota para atualizar um livro pelo seu id
    @PutMapping("/atualizar/{id}")
    // anotações do swagger
    @Operation(description = "Atualiza um livro")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. É possível atualizar quantos e quaisquer atributos quiser.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarLivroBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o livro atualizado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarLivroResponse)))
    // rota em si
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        livro.setId(id);
        Livro livroAtualizado = livroService.atualizarLivro(id, livro);
        return ResponseEntity.ok(livroAtualizado);
    }

    // rota para excluir um livro pelo seu id
    @DeleteMapping("/excluir/{id}")
    // anotações do swagger
    @Operation(description = "Remove um livro.")
    @ApiResponse(responseCode = "200", description = "Retorna uma mensagem de sucesso",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Livro excluido com sucesso!")))
    // rota em si
    public ResponseEntity<String> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.ok("Livro excluido com sucesso!");
    }

    // rota para obter um livro pelo seu id
    @GetMapping("/{id}")
    // anotações do swagger
    @Operation(description = "Obtém os dados de um livro por seu ID.")
    @ApiResponse(responseCode = "200", description = "Retorna o livro buscado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterLivroPorIdResponse)))
    // rota em si
    public ResponseEntity<Livro> obterLivroPorId(@PathVariable Long id) {
        Livro livro = livroService.obterLivroPorId(id);
        return ResponseEntity.ok(livro);
    }

    // rota para obter todos os livros
    @GetMapping
    // anotações do swagger
    @Operation(description = "Obtém todos os livros.")
    @ApiResponse(responseCode = "200", description = "Retorna todos os livros",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterTodosLivrosResponse)))
    // rota em si
    public ResponseEntity<List<Livro>> obterTodosLivros() {
        List<Livro> livros = livroService.obterTodosLivros();
        return ResponseEntity.ok(livros);
    }
}
