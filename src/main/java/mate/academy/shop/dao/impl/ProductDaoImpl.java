package mate.academy.shop.dao.impl;

import mate.academy.shop.dao.ProductDao;
import mate.academy.shop.db.Storage;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.products.size())
                .filter(p -> Storage.products.get(p).getId().equals(product.getId()))
                .forEach(i -> Storage.products.set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        IntStream.range(0, Storage.products.size())
                .filter(x -> Storage.products.get(x).getId().equals(id))
                .forEach(Storage.products::remove);
        return true;
    }
}
