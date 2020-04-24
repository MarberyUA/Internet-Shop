package mate.academy.shop.dao.impl;

import mate.academy.shop.dao.ShoppingCardDao;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.db.Storage;
import mate.academy.shop.model.User;

import java.util.List;
import java.util.stream.IntStream;

@Dao
public class ShoppingCardDaoImpl implements ShoppingCardDao {
    @Override
    public ShoppingCard create(User user) {
        ShoppingCard shoppingCard = new ShoppingCard();
        shoppingCard.setUser(user);
        Storage.addShoppingCard(shoppingCard);
        return shoppingCard;
    }

    @Override
    public ShoppingCard get(Long id) {
        return Storage.shoppingCards
                .stream()
                .filter(sh -> sh.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<ShoppingCard> getAll() {
        return Storage.shoppingCards;
    }

    @Override
    public ShoppingCard update(ShoppingCard shoppingCard) {
        IntStream.range(0, Storage.shoppingCards.size())
                .filter(sh -> Storage.shoppingCards.get(sh).getId().equals(shoppingCard.getId()))
                .forEach(s -> Storage.shoppingCards.set(s, shoppingCard));
        return shoppingCard;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.shoppingCards.removeIf(sh -> sh.getId().equals(id));
    }
}
