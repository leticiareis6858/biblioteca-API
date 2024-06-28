package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroDao {
    Livro save(Livro livro);
    Livro update(Livro livro);
    void delete(Long id);
    Livro findById(Long id);
    List<Livro> findAll();
}
