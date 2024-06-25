package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.entity.Livro;
import com.uninter.biblioteca.model.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro adicionarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro atualizarLivro(Long id, Livro livro) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

        // Atualize apenas os campos não nulos do livro existente com os novos valores
        if (livro.getTitulo() != null) {
            livroExistente.setTitulo(livro.getTitulo());
        }
        if (livro.getAutor() != null) {
            livroExistente.setAutor(livro.getAutor());
        }
        if (livro.getIsbn() != null) {
            livroExistente.setIsbn(livro.getIsbn());
        }
        if (livro.getGenero() != null) {
            livroExistente.setGenero(livro.getGenero());
        }
        if (livro.getDisponibilidade() != null) {
            livroExistente.setDisponibilidade(livro.getDisponibilidade());
        }

        return livroRepository.save(livroExistente);
    }

    public void removerLivro(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro obterLivroPorId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livro> obterTodosLivros() {
        return livroRepository.findAll();
    }
}
