package com.uninter.biblioteca.controller;

import com.uninter.biblioteca.model.entity.Livro;
import com.uninter.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping("/adicionar")
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.adicionarLivro(livro);
        return ResponseEntity.ok(novoLivro);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        livro.setId(id);
        Livro livroAtualizado = livroService.atualizarLivro(id, livro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.ok("Livro excluido com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> obterLivroPorId(@PathVariable Long id) {
        Livro livro = livroService.obterLivroPorId(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> obterTodosLivros() {
        List<Livro> livros = livroService.obterTodosLivros();
        return ResponseEntity.ok(livros);
    }
}
