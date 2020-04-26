package mate.academy.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.shop.dao.ShoppingCardDao;
import mate.academy.shop.db.Storage;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.ShoppingCard;

@Dao
public class ShoppingCardDaoImpl implements ShoppingCardDao {
    @Override
    public ShoppingCard create(ShoppingCard obj) {
        Storage.addShoppingCard(obj);
        return obj;
    }

    @Override
    public Optional<ShoppingCard> get(Long id) {
        return Storage.shoppingCards
                .stream()
                .filter(sh -> sh.getId().equals(id))
                .findFirst();
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
