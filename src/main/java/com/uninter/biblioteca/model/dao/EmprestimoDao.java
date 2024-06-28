package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Emprestimo;

import java.util.List;

public interface EmprestimoDao extends Dao<Emprestimo, Long>{
    Emprestimo save(Emprestimo emprestimo);
    Emprestimo update(Emprestimo emprestimo);
    void delete(Long id);
    Emprestimo findById(Long id);
    List<Emprestimo> findAll();
}
