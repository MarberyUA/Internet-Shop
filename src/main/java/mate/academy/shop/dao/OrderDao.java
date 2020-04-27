package mate.academy.shop.dao;

import mate.academy.shop.model.Order;
import mate.academy.shop.model.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getUserOrders(User user);
}
