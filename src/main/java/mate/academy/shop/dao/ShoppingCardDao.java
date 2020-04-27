package mate.academy.shop.dao;

import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;

import java.util.List;

public interface ShoppingCardDao {
    ShoppingCard create(User user);

    ShoppingCard get(Long id);

    List<ShoppingCard> getAll();

    ShoppingCard update(ShoppingCard shoppingCard);

    boolean delete(Long id);
}
