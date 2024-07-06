package com.uninter.biblioteca.repository;

import com.uninter.biblioteca.model.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// repositorio para a entidade emprestimo
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
