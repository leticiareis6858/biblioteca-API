package com.uninter.biblioteca.controller;

import com.uninter.biblioteca.model.entity.Emprestimo;
import com.uninter.biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/criar")
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody Emprestimo emprestimo) {
        Emprestimo novoEmprestimo = emprestimoService.criarEmprestimo(emprestimo);
        return ResponseEntity.ok(novoEmprestimo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        emprestimo.setId(id);
        Emprestimo emprestimoAtualizado = emprestimoService.atualizarEmprestimo(emprestimo);
        return ResponseEntity.ok(emprestimoAtualizado);
    }
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> removerEmprestimo(@PathVariable Long id) {
        emprestimoService.removerEmprestimo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> obterEmprestimoPorId(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.obterEmprestimoPorId(id);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> obterTodosEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.obterTodosEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }
}
