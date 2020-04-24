package mate.academy.shop.dao;

import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.User;

import java.util.List;

public interface OrderDao {
    Order create(Order order, User user);

    Order get(Long id);

    List<Order> getAll();

    Order update(Order order);

    boolean delete(Long id);
}
