package mate.academy.shop.db;

import mate.academy.shop.model.Product;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Long userId = 0L;
    private static Long productId = 0L;
    private static Long orderId = 0L;
    private static Long shoppingCardId = 0L;
    public static final List<Product> products = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<ShoppingCard> shoppingCards = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();

    public static void addUser(User user) {
    }

    public static void addProduct(Product product) {
        productId++;
        product.setId(productId);
        products.add(product);

    }

    public static void addOrder(Order order) {

    }

    public static void addShoppingCard(ShoppingCard shoppingCard) {

    }
}
