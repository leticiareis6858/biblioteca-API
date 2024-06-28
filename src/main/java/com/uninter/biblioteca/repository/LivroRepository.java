package com.uninter.biblioteca.repository;

import com.uninter.biblioteca.model.dao.AbstractDao;
import com.uninter.biblioteca.model.dao.LivroDao;
import com.uninter.biblioteca.model.entity.Livro;
import org.springframework.stereotype.Repository;

@Repository
public class LivroRepository extends AbstractDao<Livro, Long> implements LivroDao {

}