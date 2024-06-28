package com.uninter.biblioteca.controller;

import com.uninter.biblioteca.controller.dto.EmprestimoDTO;
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
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        Emprestimo novoEmprestimo = emprestimoService.criarEmprestimo(emprestimoDTO);
        return ResponseEntity.ok(novoEmprestimo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        emprestimo.setId(id);
        Emprestimo emprestimoAtualizado = emprestimoService.atualizarEmprestimo(id, emprestimo);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<Emprestimo> devolverEmprestimo(@PathVariable Long id){
        Emprestimo emprestimoDevolvido = emprestimoService.devolverEmprestimo(id);
        return ResponseEntity.ok(emprestimoDevolvido);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> removerEmprestimo(@PathVariable Long id) {
        emprestimoService.removerEmprestimo(id);
        return ResponseEntity.ok("Emprestimo excluido com sucesso!");
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
