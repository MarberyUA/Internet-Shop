package mate.academy.shop.service;

import java.util.List;

public interface GenericService<T> {
    T get(Long id);

    T create(T obj);

    T update(T obj);

    boolean delete(Long id);

    List<T> getAll();
}
