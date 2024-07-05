package com.uninter.biblioteca.service;

import com.uninter.biblioteca.controller.dto.LivroDTO;
import com.uninter.biblioteca.model.entity.Livro;

import static com.uninter.biblioteca.model.enumeration.Disponibilidade.DISPONIVEL;

import com.uninter.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// classe de serviço para livro
@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    // método para criar um livro
    public Livro adicionarLivro(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setIsbn(livroDTO.getIsbn());
        livro.setGenero(livroDTO.getGenero());
        if(livroDTO.getDisponibilidade() == null){
            livro.setDisponibilidade(DISPONIVEL);
        } else {
            livro.setDisponibilidade(livroDTO.getDisponibilidade());
        }
        return livroRepository.save(livro);
    }

    // método para atualizar um livro
    public Livro atualizarLivro(Long id, LivroDTO livroDTO) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

        if (livroDTO.getTitulo() != null) {
            livroExistente.setTitulo(livroDTO.getTitulo());
        }

        if (livroDTO.getAutor() != null) {
            livroExistente.setAutor(livroDTO.getAutor());
        }

        if (livroDTO.getIsbn() != null) {
            livroExistente.setIsbn(livroDTO.getIsbn());
        }

        if (livroDTO.getGenero() != null) {
            livroExistente.setGenero(livroDTO.getGenero());
        }

        if (livroDTO.getDisponibilidade() != null) {
            livroExistente.setDisponibilidade(livroDTO.getDisponibilidade());
        }

        return livroRepository.save(livroExistente);
    }

    // método para excluir um livro
    public void removerLivro(Long id) {
        livroRepository.deleteById(id);
    }

    // método para obter um livro pelo seu id
    public Livro obterLivroPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));
    }

    // método para obter todos os livros
    public List<Livro> obterTodosLivros() {
        List<Livro> livros=livroRepository.findAll();
        if(livros.isEmpty()){
            throw new RuntimeException("Nenhum livro encontrado!");
        }
        return livros;
    }
}
