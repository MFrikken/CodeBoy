package com.codeboy.dao;

import java.util.logging.Logger;

abstract class Dao<T, K> {
    protected static final Logger LOGGER = Logger.getLogger(Dao.class.getName());

    abstract boolean create(T entity);

    abstract T read(K key);

    abstract T update(K key, T newEntity);

    abstract boolean delete(K key);
}
