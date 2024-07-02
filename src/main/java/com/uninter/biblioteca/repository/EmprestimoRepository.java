package com.uninter.biblioteca.repository;

import com.uninter.biblioteca.model.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// repositorio para a entidade emprestimo
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
