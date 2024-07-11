package com.uninter.biblioteca.model.dao;

import com.uninter.biblioteca.model.entity.Emprestimo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// classe de implementação do emprestimoDao
@Repository
@Transactional
public class EmprestimoDaoImpl extends AbstractDao<Emprestimo, Long> implements EmprestimoDao {

    @Override
    public Emprestimo save(Emprestimo emprestimo) {
        super.save(emprestimo);
        return emprestimo;
    }

    @Override
    public void update(Emprestimo emprestimo) {
        super.update(emprestimo);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public Emprestimo findById(Long id) {
        return super.findById(id);
    }

    @Override
    public java.util.List<Emprestimo> findAll() {
        return super.findAll();
    }
}