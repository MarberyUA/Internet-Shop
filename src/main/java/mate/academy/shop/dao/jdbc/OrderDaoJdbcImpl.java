package mate.academy.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.shop.dao.OrderDao;
import mate.academy.shop.dao.ProductDao;
import mate.academy.shop.dao.UserDao;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.User;
import mate.academy.shop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Inject
    UserDao userDao;

    @Inject
    ProductDao productDao;

    @Override
    public List<Order> getUserOrders(User user) {
        String query = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = get(resultSet.getLong(1)).get();
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user orders", e);
        }
    }

    @Override
    public Order create(Order obj) {
        String orderQuery = "INSERT INTO orders (user_id) VALUES (?);";
        String addingQuery = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            String[] generatedColumns = {"order_id"};
            PreparedStatement statement = connection.prepareStatement(orderQuery, generatedColumns);
            statement.setLong(1, obj.getUser().getId());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                obj.setId(resultSet.getLong(1));
            }
            insertProductInOrder(connection, obj, addingQuery);
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user orders", e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        String productsQuery = "SELECT * FROM orders_products WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Order order = new Order();
            if (resultSet.next()) {
                order.setUser(userDao.get(resultSet.getLong("user_id")).get());
                order.setId(id);
            }
            PreparedStatement preparedStatement = connection.prepareStatement(productsQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSetProducts = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSetProducts.next()) {
                Product product = productDao.get(resultSetProducts.getLong("product_id")).get();
                products.add(product);
            }
            order.setProductsInOrder(products);
            return Optional.of(order);
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user orders", e);
        }
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setUser(userDao.get(resultSet.getLong("user_id")).get());
                order.setId(resultSet.getLong("order_id"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user orders", e);
        }
    }

    @Override
    public Order update(Order obj) {
        String deletingQuery = "DELETE FROM orders_products WHERE card_id = ?";
        String addingQuery = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement deletingStatement = connection.prepareStatement(deletingQuery);
            deletingStatement.setLong(1, obj.getId());
            deletingStatement.execute();
            insertProductInOrder(connection, obj, addingQuery);
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user orders", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        String productsDeletingQuery = "DELETE FROM orders_products WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(productsDeletingQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user orders", e);
        }
    }

    public Order getOrderDetail(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("order_id"));
        order.setUser(userDao.get(resultSet.getLong("user_id")).get());
        return order;
    }

    public void insertProductInOrder(Connection connection, Order order, String query)
            throws SQLException {
        for (int i = 0; i < order.getProductsInOrder().size(); i++) {
            PreparedStatement addingStatement = connection.prepareStatement(query);
            addingStatement.setLong(1, order.getId());
            addingStatement.setLong(2, order.getProductsInOrder().get(i).getId());
            addingStatement.execute();
        }
    }
}
