package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Livro;
import java.util.List;

public interface LivroDao {
    void save(Livro livro);
    void update(Livro livro);
    void delete(Long id);
    Livro findById(Long id);
    List<Livro> findAll();
}
