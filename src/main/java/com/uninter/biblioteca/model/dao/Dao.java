package com.uninter.biblioteca.model.dao;

import java.util.List;

public interface Dao<T, ID> {
    T save(T entity);
    T update(T entity);
    void delete(ID id);
    T findById(ID id);
    List<T> findAll();
}
