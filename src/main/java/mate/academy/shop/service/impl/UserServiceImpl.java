package mate.academy.shop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.shop.dao.UserDao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.User;
import mate.academy.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    UserDao userDao;

    @Override
    public Optional<User> getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}
