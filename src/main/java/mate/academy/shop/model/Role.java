package mate.academy.shop.model;

public class Role {
    private Long id;
    private RoleName roleName;

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public enum RoleName {
        USER, ADMIN;
    }
}
