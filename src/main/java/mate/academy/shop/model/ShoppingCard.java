package mate.academy.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {
    private User user;
    private Long id;
    private List<Product> productsInShopping = new ArrayList<>();

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

    public void addProductToCard(Product product) {
        productsInShopping.add(product);
    }

    @Override
    public String toString() {
        return productsInShopping.toString();
    }
}
