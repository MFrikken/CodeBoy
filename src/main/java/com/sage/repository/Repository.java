package com.sage.repository;

import java.util.List;

public interface Repository<T, K> {
    boolean save(T entity);

    T getByID(K key);

    List<T> getAll();

    T update(K key, T newEntity);

    boolean delete(K key);

    boolean deleteAll();
}
