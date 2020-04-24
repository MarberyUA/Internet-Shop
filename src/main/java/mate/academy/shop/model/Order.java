package mate.academy.shop.model;

import java.util.List;

public class Order {
    private Long id;
    private User user;
    private List<Product> productsInOrder;


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

    public List<Product> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(List<Product> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }
}
