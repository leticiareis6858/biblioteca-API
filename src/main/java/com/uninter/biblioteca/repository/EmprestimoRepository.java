package com.uninter.biblioteca.repository;

import com.uninter.biblioteca.model.dao.AbstractDao;
import com.uninter.biblioteca.model.dao.EmprestimoDao;
import com.uninter.biblioteca.model.entity.Emprestimo;
import org.springframework.stereotype.Repository;

@Repository
public class EmprestimoRepository extends AbstractDao<Emprestimo, Long> implements EmprestimoDao {

}
