package com.sage.controller;

import java.util.List;

public interface Controller<T> {
    
    public T fetch(Integer id);

    public List<T> fetchAll();
}
