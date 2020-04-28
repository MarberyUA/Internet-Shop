package mate.academy.shop.dao;

import java.util.List;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.User;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getUserOrders(User user);
}
