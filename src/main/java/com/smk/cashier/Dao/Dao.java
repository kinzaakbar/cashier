package com.smk.cashier.dao;

import com.smk.cashier.model.Stok;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T,I> {
    Optional<T> get(int id);
    Collection<T> getAll();
    Optional<I> save(T t);
    void update(T t);
    void delete(T t);

    Collection<Stok> search(String keyword);
}
