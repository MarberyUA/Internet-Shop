package mate.academy.shop.service.impl;

import mate.academy.shop.dao.ShoppingCardDao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;
import mate.academy.shop.service.ShoppingCardService;

import java.util.List;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {
    @Inject
    ShoppingCardDao shoppingCardDao;

    @Override
    public ShoppingCard create(User user) {
        return shoppingCardDao.create(user);
    }

    @Override
    public ShoppingCard addProduct(ShoppingCard shoppingCart, Product product) {
        return shoppingCardDao.addProductToCard(shoppingCart, product);
    }

    @Override
    public boolean deleteProduct(ShoppingCard shoppingCart, Product product) {
        return shoppingCardDao.deleteProduct(shoppingCart, product);
    }

    @Override
    public void clear(ShoppingCard shoppingCart) {
        shoppingCardDao.get(shoppingCart.getId()).getProductsInShopping().clear();
    }

    @Override
    public ShoppingCard getByUserId(Long userId) {
        return shoppingCardDao.getAll()
                .stream()
                .filter(sc -> sc.getUser().getId().equals(userId))
                .findFirst()
                .get();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCard shoppingCart) {
        return shoppingCardDao.get(shoppingCart.getId()).getProductsInShopping();
    }
}
