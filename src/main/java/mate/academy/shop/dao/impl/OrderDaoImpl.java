package mate.academy.shop.dao.impl;

import mate.academy.shop.dao.OrderDao;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.Order;
import mate.academy.shop.db.Storage;
import mate.academy.shop.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(order -> order.getUser().getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(i -> Storage.orders.get(i).getId().equals(order.getId()))
                .forEach(o -> Storage.orders.set(o, order));
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.removeIf(order -> order.getId().equals(id));
    }
}
