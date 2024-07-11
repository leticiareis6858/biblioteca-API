package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Livro;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// classe de implementação do livroDao
@Repository
@Transactional
public class LivroDaoImpl extends AbstractDao<Livro, Long> implements LivroDao {

    @Override
    public Livro save(Livro livro) {
        super.save(livro);
        return livro;
    }

    @Override
    public void update(Livro livro) {
        super.update(livro);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public Livro findById(Long id) {
        return super.findById(id);
    }

    @Override
    public java.util.List<Livro> findAll() {
        return super.findAll();
    }
}