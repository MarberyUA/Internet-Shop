package mate.academy.shop.dao;

import mate.academy.shop.model.ShoppingCard;

public interface ShoppingCardDao extends GenericDao<ShoppingCard> {
    ShoppingCard getByUserId(Long userId);
}
