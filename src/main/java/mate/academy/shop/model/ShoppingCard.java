package mate.academy.shop.model;

import java.util.List;

public class ShoppingCard {
    private User user;
    private Long id;
    private List<Product> productsInShopping;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProductsInShopping() {
        return productsInShopping;
    }

    public void setProductsInShopping(List<Product> productsInShopping) {
        this.productsInShopping = productsInShopping;
    }
}
