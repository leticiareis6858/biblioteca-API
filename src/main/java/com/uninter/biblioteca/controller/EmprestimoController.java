package com.uninter.biblioteca.controller;

import com.uninter.biblioteca.controller.dto.EmprestimoDTO;
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

import static com.uninter.biblioteca.swagger.ExemplosRequestBody.*;
import static com.uninter.biblioteca.swagger.ExemplosResponses.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/criar")
    @Operation(description = "Cria um emprestimo")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. Não é necessário informar a data de devolução e nem a de empréstimo.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarEmprestimoBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o emprestimo criado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = criarEmprestimoResponse)))
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        Emprestimo novoEmprestimo = emprestimoService.criarEmprestimo(emprestimoDTO);
        return ResponseEntity.ok(novoEmprestimo);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(description = "Atualiza um emprestimo")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Exemplo de Request Body. É possível atualizar quantos e quaisquer atributos quiser.",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarEmprestimoBody)))
    @ApiResponse(responseCode = "200", description = "Retorna o emprestimo atualizado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = atualizarEmprestimoResponse)))
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        emprestimo.setId(id);
        Emprestimo emprestimoAtualizado = emprestimoService.atualizarEmprestimo(id, emprestimo);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    @PutMapping("/devolver/{id}")
    @Operation(description="Devolve um emprestimo e automaticamente atualiza o status do livro para DISPONIVEL e o status do emprestimo para DEVOLVIDO.")
    @ApiResponse(responseCode = "200", description = "Retorna o emprestimo devolvido",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = devolverEmprestimoResponse )))
    public ResponseEntity<Emprestimo> devolverEmprestimo(@PathVariable Long id) {
        Emprestimo emprestimoDevolvido = emprestimoService.devolverEmprestimo(id);
        return ResponseEntity.ok(emprestimoDevolvido);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(description = "Remove um emprestimo.")
    @ApiResponse(responseCode = "200", description = "Retorna uma mensagem de sucesso",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Emprestimo excluido com sucesso!")))
    public ResponseEntity<String> removerEmprestimo(@PathVariable Long id) {
        emprestimoService.removerEmprestimo(id);
        return ResponseEntity.ok("Emprestimo excluido com sucesso!");
    }

    @GetMapping("/{id}")
    @Operation(description = "Obtém os dados de um emprestimo por seu ID.")
    @ApiResponse(responseCode = "200", description = "Retorna o emprestimo buscado",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterEmprestimoPorIdResponse)))
    public ResponseEntity<Emprestimo> obterEmprestimoPorId(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.obterEmprestimoPorId(id);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping
    @Operation(description = "Obtém todos os emprestimos.")
    @ApiResponse(responseCode = "200", description = "Retorna todos os emprestimos",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = obterTodosEmprestimosResponse)))
    public ResponseEntity<List<Emprestimo>> obterTodosEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.obterTodosEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }
}
