package mate.academy.shop.dao;

import mate.academy.shop.model.User;

import java.util.List;

public interface UserDao {
    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);

}
