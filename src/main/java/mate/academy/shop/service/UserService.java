package mate.academy.shop.service;

import java.util.List;
import java.util.Optional;
import mate.academy.shop.model.Role;
import mate.academy.shop.model.User;

public interface UserService extends GenericService<User> {
    Optional<User> getByLogin(String login);

    List<Role.RoleName> getUserRolesById(Long id);
}
