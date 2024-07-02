package com.uninter.biblioteca.repository;

import com.uninter.biblioteca.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// repositorio para a entidade livro
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}