package mate.academy.shop.service;

import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;

import java.util.List;

public interface ShoppingCardService {
    ShoppingCard create(User user);

    ShoppingCard addProduct(ShoppingCard shoppingCart, Product product);

    boolean deleteProduct(ShoppingCard shoppingCart, Product product);

    void clear(ShoppingCard shoppingCart);

    ShoppingCard getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCard shoppingCart);
}
