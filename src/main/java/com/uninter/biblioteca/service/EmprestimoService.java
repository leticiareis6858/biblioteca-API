package com.uninter.biblioteca.service;

import com.uninter.biblioteca.model.entity.Emprestimo;
import com.uninter.biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public void removerEmprestimo(Long id) {
        emprestimoRepository.deleteById(id);
    }

    public Emprestimo obterEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public List<Emprestimo> obterTodosEmprestimos() {
        return emprestimoRepository.findAll();
    }

}
