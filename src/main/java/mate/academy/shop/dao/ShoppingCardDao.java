package mate.academy.shop.dao;

import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;

import java.util.List;

public interface ShoppingCardDao {
    ShoppingCard create(User user);

    ShoppingCard get(Long id);

    ShoppingCard addProductToCard(ShoppingCard shoppingCard, Product product);

    List<ShoppingCard> getAll();

    ShoppingCard update(ShoppingCard shoppingCard);

    boolean deleteProduct(ShoppingCard shoppingCart, Product product);

    boolean delete(Long id);
}
