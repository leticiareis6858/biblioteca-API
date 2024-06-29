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

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping("/adicionar")
    @Operation(description = "Cria um livro")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. Não é necessário informar a disponibilidade.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarLivroBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o livro criado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarLivroResponse)))
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.adicionarLivro(livro);
        return ResponseEntity.ok(novoLivro);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(description = "Atualiza um livro")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. É possível atualizar quantos e quaisquer atributos quiser.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarLivroBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o livro atualizado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarLivroResponse)))
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        livro.setId(id);
        Livro livroAtualizado = livroService.atualizarLivro(id, livro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(description = "Remove um livro.")
    @ApiResponse(responseCode = "200", description = "Retorna uma mensagem de sucesso",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Livro excluido com sucesso!")))
    public ResponseEntity<String> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.ok("Livro excluido com sucesso!");
    }

    @GetMapping("/{id}")
    @Operation(description = "Obtém os dados de um livro por seu ID.")
    @ApiResponse(responseCode = "200", description = "Retorna o livro buscado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterLivroPorIdResponse)))
    public ResponseEntity<Livro> obterLivroPorId(@PathVariable Long id) {
        Livro livro = livroService.obterLivroPorId(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    @Operation(description = "Obtém todos os livros.")
    @ApiResponse(responseCode = "200", description = "Retorna todos os livros",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterTodosLivrosResponse)))
    public ResponseEntity<List<Livro>> obterTodosLivros() {
        List<Livro> livros = livroService.obterTodosLivros();
        return ResponseEntity.ok(livros);
    }
}
