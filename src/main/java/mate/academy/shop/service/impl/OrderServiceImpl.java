package mate.academy.shop.service.impl;

import mate.academy.shop.dao.OrderDao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.User;
import mate.academy.shop.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    OrderDao orderDao;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        Order order = new Order();
        order.setProductsInOrder(products);
        order.setUser(user);
        orderDao.create(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAll()
                .stream()
                .filter(order -> order.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
