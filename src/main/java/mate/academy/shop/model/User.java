package mate.academy.shop.model;

import java.util.List;
import java.util.ArrayList;

public class User {
    private String name;
    private Long id;
    private String password;
    private List<Role> roles = new ArrayList<>();

    public User() {
        Role role = new Role(Role.RoleName.USER);
        roles.add(role);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
