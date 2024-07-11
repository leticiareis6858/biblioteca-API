package com.uninter.biblioteca.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import jakarta.persistence.*;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// classe Dao abstrata
public abstract class AbstractDao <T, PK extends Serializable> {
    @SuppressWarnings("unchecked")
    private final Class<T> entityClass =
            (Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager entityManager;

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void update(T entity) {

        entityManager.merge(entity);
    }

    public void delete(PK id) {

        entityManager.remove(entityManager.getReference(entityClass, id));
    }

    public T findById(PK id) {

        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {

        return entityManager
                .createQuery("from " + entityClass.getSimpleName(), entityClass)
                .getResultList();
    }

}