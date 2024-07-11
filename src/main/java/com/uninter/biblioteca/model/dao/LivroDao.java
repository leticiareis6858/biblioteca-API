package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Livro;

import java.util.List;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// interface livroDao
public interface LivroDao {
    Livro save(Livro livro);
    void update(Livro livro);
    void delete(Long id);
    Livro findById(Long id);
    List<Livro> findAll();
}
