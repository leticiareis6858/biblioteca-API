package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Emprestimo;

import java.util.List;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// interface EmprestimoDao
public interface EmprestimoDao {
        Emprestimo save(Emprestimo emprestimo);
        void update(Emprestimo emprestimo);
        void delete(Long id);
        Emprestimo findById(Long id);
        List<Emprestimo> findAll();
}
