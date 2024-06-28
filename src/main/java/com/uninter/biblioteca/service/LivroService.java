package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.dao.LivroDao;
import com.uninter.biblioteca.model.entity.Livro;

import static com.uninter.biblioteca.model.enumeration.Disponibilidade.DISPONIVEL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroDao dao;

    @Autowired
    public LivroService(@Qualifier("livroDao") LivroDao dao) {
        this.dao = dao;
    }

    public Livro adicionarLivro(Livro livro) {
        if (livro.getDisponibilidade() == null) {
            livro.setDisponibilidade(DISPONIVEL);
        }

        dao.save(livro);
        return livro;
    }

    public Livro atualizarLivro(Long id, Livro livro) {
        Livro livroExistente = dao.findById(id);
        if (livroExistente != null) {
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

            return dao.update(livroExistente);
        } else {
            throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }

    }

    public void removerLivro(Long id) {
        dao.delete(id);
    }

    public Livro obterLivroPorId(Long id) {
        Livro livroExistente = dao.findById(id);
        if (livroExistente != null) {
            return livroExistente;
        } else {
            throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }
    }

    public List<Livro> obterTodosLivros() {
        return dao.findAll();
    }
}
