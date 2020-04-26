package mate.academy.shop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    T create(T obj);

    Optional<T> get(Long id);

    List<T> getAll();

    T update(T obj);

    boolean delete(Long id);
}
