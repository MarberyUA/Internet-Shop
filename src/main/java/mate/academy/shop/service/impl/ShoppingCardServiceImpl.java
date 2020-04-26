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
    ShoppingCardDao shoppingCardDao;

    @Override
    public ShoppingCard create(ShoppingCard shoppingCard,User user) {
        shoppingCardDao.create(shoppingCard).setUser(user);
        return shoppingCard;
    }

    @Override
    public ShoppingCard addProduct(ShoppingCard shoppingCart, Product product) {
        shoppingCardDao.get(shoppingCart.getId()).get().addProductToCard(product);
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCard shoppingCart, Product product) {
        return shoppingCardDao.get(shoppingCart.getId()).get().getProductsInShopping()
                .removeIf(pr -> pr.getId().equals(product.getId()));
    }

    @Override
    public void clear(ShoppingCard shoppingCart) {
        shoppingCardDao.get(shoppingCart.getId()).get().getProductsInShopping().clear();
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
        return shoppingCardDao.get(shoppingCart.getId()).get().getProductsInShopping();
    }
}
