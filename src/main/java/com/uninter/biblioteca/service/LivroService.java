package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.dao.LivroDaoImpl;
import com.uninter.biblioteca.model.entity.Livro;

import static com.uninter.biblioteca.model.entity.enumeration.Disponibilidade.DISPONIVEL;

import org.springframework.stereotype.Service;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

import java.util.List;

// classe de serviço para livro
@Service
public class LivroService {

    private final LivroDaoImpl livroDao;

    public LivroService(LivroDaoImpl livroDao){
        this.livroDao = livroDao;
    }

    // método para criar um livro
    public Livro adicionarLivro(Livro livro) {
        livro = new Livro();
        livro.setTitulo(livro.getTitulo());
        livro.setAutor(livro.getAutor());
        livro.setIsbn(livro.getIsbn());
        livro.setGenero(livro.getGenero());
        if(livro.getDisponibilidade() == null){
            livro.setDisponibilidade(DISPONIVEL);
        } else {
            livro.setDisponibilidade(livro.getDisponibilidade());
        }
        return livroDao.save(livro);
    }

    // método para atualizar um livro
    public Livro atualizarLivro(Long id, Livro livro) {
        Livro livroExistente = livroDao.findById(id);
        if (livroExistente == null) {
           throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }

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

        livroDao.update(livroExistente);
        return livroExistente;
    }

    // método para excluir um livro
    public void removerLivro(Long id) {
        livroDao.delete(id);
    }

    // método para obter um livro pelo seu id
    public Livro obterLivroPorId(Long id) {
       Livro livroExistente=livroDao.findById(id);
         if(livroExistente == null) {
             throw new RuntimeException("Livro não encontrado com o ID: " + id);
         }
         return livroExistente;
    }

    // método para obter todos os livros
    public List<Livro> obterTodosLivros() {
        List<Livro> livros=livroDao.findAll();
        if(livros.isEmpty()){
            throw new RuntimeException("Nenhum livro encontrado!");
        }
        return livros;
    }
}
