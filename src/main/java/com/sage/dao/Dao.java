package com.sage.dao;

import java.util.logging.Logger;

import com.sage.utility.JPAManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

abstract class Dao<T, K> {
    protected static final Logger LOGGER = Logger.getLogger(Dao.class.getName());

    protected EntityManager em;
    protected EntityTransaction tx;

    protected Dao() {
        em = JPAManager.getEntityManager();
        tx = em.getTransaction();
    }

    abstract boolean create(T entity);

    abstract T read(K key);

    abstract T update(K key, T newEntity);

    abstract boolean delete(K key);
}
