package mate.academy.shop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.shop.dao.UserDao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.Role;
import mate.academy.shop.model.User;
import mate.academy.shop.service.UserService;
import mate.academy.shop.util.HashUtil;

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
        StringBuilder salt = new StringBuilder();
        for (byte b : HashUtil.getSalt()) {
            salt.append(String.format("%02x", b));
        }
        user.setSalt(salt.toString());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), salt.toString().getBytes()));
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

    @Override
    public List<Role.RoleName> getUserRolesById(Long id) {
        return userDao.getUserRolesById(id);
    }
}
