package mate.academy.shop.security.impl;

import mate.academy.shop.exceptions.AuthenticationException;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.User;
import mate.academy.shop.security.AuthenticationService;
import mate.academy.shop.service.UserService;
import mate.academy.shop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDb = userService.getByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect username or password"));
        if (userFromDb.getPassword().equals(HashUtil.hashPassword(password,
                userFromDb.getSalt()))) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
