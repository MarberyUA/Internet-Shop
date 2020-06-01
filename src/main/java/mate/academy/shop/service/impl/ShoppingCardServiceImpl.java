package mate.academy.shop.service.impl;

import java.util.List;
import mate.academy.shop.dao.ShoppingCardDao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;
import mate.academy.shop.service.ShoppingCardService;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {
    @Inject
    private ShoppingCardDao shoppingCardDao;

    @Override
    public ShoppingCard create(ShoppingCard shoppingCard,User user) {
        shoppingCard.setUser(user);
        shoppingCardDao.create(shoppingCard);
        return shoppingCard;
    }

    @Override
    public ShoppingCard addProduct(ShoppingCard shoppingCart, Product product) {
        shoppingCart.addProductToCard(product);
        shoppingCart = shoppingCardDao.update(shoppingCart);
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCard shoppingCart, Product product) {
        boolean isDeleted = false;
        for (int i = 0; i < shoppingCart.getProductsInShopping().size(); i++) {
            if (shoppingCart.getProductsInShopping().get(i).getId().equals(product.getId())) {
                shoppingCart.getProductsInShopping().remove(i);
                isDeleted = true;
                break;
            }
        }
        shoppingCardDao.update(shoppingCart);
        return isDeleted;
    }

    @Override
    public void clear(ShoppingCard shoppingCart) {
        shoppingCart.getProductsInShopping().clear();
        shoppingCardDao.update(shoppingCart);
    }

    @Override
    public ShoppingCard getByUserId(Long userId) {
        return shoppingCardDao.getByUserId(userId);
    }

    @Override
    public List<Product> getAllProducts(ShoppingCard shoppingCart) {
        return shoppingCardDao.get(shoppingCart.getId()).get().getProductsInShopping();
    }
}
