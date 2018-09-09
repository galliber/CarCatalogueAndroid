package com.example.wolverine.carcatalogueandroid.repositories.base;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    List<T> getAll() throws IOException;
    void add(T item) throws IOException;
    void delete(int id) throws IOException;
    T getById(int id) throws IOException;
}
