package mate.academy.shop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;

public class Storage {
    public static final List<Order> orders = new ArrayList<>();
    public static final List<Product> products = new ArrayList<>();
    public static final List<ShoppingCard> shoppingCards = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    private static Long userId = 0L;
    private static Long productId = 0L;
    private static Long orderId = 0L;
    private static Long shoppingCardId = 0L;

    public static void addUser(User user) {
        userId++;
        user.setId(userId);
        users.add(user);
    }

    public static void addProduct(Product product) {
        productId++;
        product.setId(productId);
        products.add(product);

    }

    public static void addOrder(Order order) {
        orderId++;
        order.setId(orderId);
        orders.add(order);
    }

    public static void addShoppingCard(ShoppingCard shoppingCard) {
        shoppingCardId++;
        shoppingCard.setId(shoppingCardId);
        shoppingCards.add(shoppingCard);
    }
}
