package com.uninter.biblioteca.controller;

import com.uninter.biblioteca.model.entity.Emprestimo;
import com.uninter.biblioteca.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

import static com.uninter.biblioteca.swagger.ExemplosRequestBody.*;
import static com.uninter.biblioteca.swagger.ExemplosResponses.*;

//  controller de emprestimos
@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    // rota para criar um emprestimo
    @PostMapping("/criar")
    // anotações do swagger
    @Operation(description = "Cria um emprestimo")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. Não é necessário informar a data de devolução e nem a de empréstimo.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarEmprestimoBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o emprestimo criado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarEmprestimoResponse)))
    // rota em si
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody Map<String, Long> emprestimoDados) {
        Emprestimo novoEmprestimo = emprestimoService.criarEmprestimo(emprestimoDados);
        return ResponseEntity.ok(novoEmprestimo);
    }

    // rota para atualizar um emprestimo
    @PutMapping("/atualizar/{id}")
    // anotações do swagger
    @Operation(description = "Atualiza um emprestimo")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. É possível atualizar quantos e quaisquer atributos quiser.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarEmprestimoBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o emprestimo atualizado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarEmprestimoResponse)))
    // rota em si
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable Long id, @RequestBody Map<String, Object> emprestimoDados) {
        Emprestimo emprestimoAtualizado = emprestimoService.atualizarEmprestimo(id, emprestimoDados);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    // rota para devolver um emprestimo
    @PutMapping("/devolver/{id}")
    // anotações do swagger
    @Operation(description="Devolve um emprestimo e automaticamente atualiza o status do livro para DISPONIVEL e o status do emprestimo para DEVOLVIDO.")
    @ApiResponse(responseCode = "200", description = "Retorna o emprestimo devolvido",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = devolverEmprestimoResponse )))
    // rota em si
    public ResponseEntity<Emprestimo> devolverEmprestimo(@PathVariable Long id) {
        Emprestimo emprestimoDevolvido = emprestimoService.devolverEmprestimo(id);
        return ResponseEntity.ok(emprestimoDevolvido);
    }

    // rota para deletar um emprestimo
    @DeleteMapping("/excluir/{id}")
    // anotações do swagger
    @Operation(description = "Remove um emprestimo.")
    @ApiResponse(responseCode = "200", description = "Retorna uma mensagem de sucesso",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Emprestimo excluido com sucesso!")))
    // rota em si
    public ResponseEntity<String> removerEmprestimo(@PathVariable Long id) {
        emprestimoService.removerEmprestimo(id);
        return ResponseEntity.ok("Emprestimo excluido com sucesso!");
    }

    // rota para obter os dados de um eprestimo por seu id
    @GetMapping("/{id}")
    // anotações do swagger
    @Operation(description = "Obtém os dados de um emprestimo por seu ID.")
    @ApiResponse(responseCode = "200", description = "Retorna o emprestimo buscado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterEmprestimoPorIdResponse)))
    // rota em si
    public ResponseEntity<Emprestimo> obterEmprestimoPorId(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.obterEmprestimoPorId(id);
        return ResponseEntity.ok(emprestimo);
    }

    // rota para obter todos os emprestimos
    @GetMapping
    // anotações do swagger
    @Operation(description = "Obtém todos os emprestimos.")
    @ApiResponse(responseCode = "200", description = "Retorna todos os emprestimos",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterTodosEmprestimosResponse)))
    // rota em si
    public ResponseEntity<List<Emprestimo>> obterTodosEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.obterTodosEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }
}
