package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Livro;
import java.util.List;

public interface LivroDao {
    Livro save(Livro livro);
    Livro update(Livro livro);
    void delete(Long id);
    Livro findById(Long id);
    List<Livro> findAll();
}
