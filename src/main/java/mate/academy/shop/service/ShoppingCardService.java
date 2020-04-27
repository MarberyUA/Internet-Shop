package mate.academy.shop.service;

import java.util.List;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;

public interface ShoppingCardService {
    ShoppingCard create(ShoppingCard shoppingCard, User user);

    ShoppingCard addProduct(ShoppingCard shoppingCart, Product product);

    boolean deleteProduct(ShoppingCard shoppingCart, Product product);

    void clear(ShoppingCard shoppingCart);

    ShoppingCard getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCard shoppingCart);
}
