package mate.academy.shop.service.impl;

import mate.academy.shop.dao.ProductDao;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.Product;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        productDao.create(product);
        return product;
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
