package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.entity.Livro;

import static com.uninter.biblioteca.model.enumeration.Disponibilidade.DISPONIVEL;

import com.uninter.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public Livro adicionarLivro(Livro livro) {
        if(livro.getDisponibilidade()==null){
            livro.setDisponibilidade(DISPONIVEL);
        }
        return livroRepository.save(livro);
    }

    public Livro atualizarLivro(Long id, Livro livro) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

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
