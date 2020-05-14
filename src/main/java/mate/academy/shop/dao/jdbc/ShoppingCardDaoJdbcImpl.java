package mate.academy.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.shop.dao.ShoppingCardDao;
import mate.academy.shop.dao.UserDao;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;
import mate.academy.shop.util.ConnectionUtil;

@Dao
public class ShoppingCardDaoJdbcImpl implements ShoppingCardDao {
    @Inject
    private UserDao userDao;

    @Override
    public ShoppingCard getByUserId(Long userId) {
        String query = "SELECT * FROM shopping_card WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ShoppingCard shoppingCard = null;
            while (resultSet.next()) {
                shoppingCard = getShoppingCardDetails(resultSet);
                shoppingCard.addAllProducts(getProductsFromShoppingCard(shoppingCard.getId(),
                        connection));
            }
            return shoppingCard;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting card by user id", e);
        }
    }

    @Override
    public ShoppingCard create(ShoppingCard obj) {
        String query = "INSERT INTO shopping_card (user_id) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            String[] generatedColumns = {"card_id"};
            PreparedStatement statement = connection.prepareStatement(query, generatedColumns);
            statement.setLong(1, obj.getUser().getId());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                obj.setId(resultSet.getLong(1));
            }
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating shopping card!", e);
        }
    }

    @Override
    public Optional<ShoppingCard> get(Long id) {
        String query = "SELECT * FROM shopping_card WHERE card_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ShoppingCard shoppingCard = getShoppingCardDetails(resultSet);
                shoppingCard.addAllProducts(getProductsFromShoppingCard(shoppingCard.getId(),
                        connection));
                return Optional.of(shoppingCard);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting shopping card!", e);
        }
    }

    @Override
    public List<ShoppingCard> getAll() {
        String query = "SELECT * FROM shopping_card;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<ShoppingCard> shoppingCards = new ArrayList<>();
            while (resultSet.next()) {
                ShoppingCard shoppingCard = getShoppingCardDetails(resultSet);
                shoppingCards.add(shoppingCard);
            }
            return shoppingCards;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all shopping card!", e);
        }
    }

    @Override
    public ShoppingCard update(ShoppingCard obj) {
        String deletingQuery = "DELETE FROM shopping_cards_products WHERE card_id = ?";
        String addingQuery = "INSERT INTO shopping_cards_products (card_id, product_id) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement deletingStatement = connection.prepareStatement(deletingQuery);
            deletingStatement.setLong(1, obj.getId());
            deletingStatement.execute();
            for (int i = 0; i < obj.getProductsInShopping().size(); i++) {
                PreparedStatement addingStatement = connection.prepareStatement(addingQuery);
                addingStatement.setLong(1, obj.getId());
                addingStatement.setLong(2, obj.getProductsInShopping().get(i).getId());
                addingStatement.execute();
            }
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException("Error while update the shopping card!", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM shopping_card WHERE card_id = ?";
        String productsDeletingQuery = "DELETE FROM shopping_cards_products WHERE card_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.execute();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(productsDeletingQuery);
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting the shopping card!", e);
        }
    }

    private ShoppingCard getShoppingCardDetails(ResultSet resultSet) throws SQLException {
        ShoppingCard shoppingCard = new ShoppingCard();
        User user = userDao.get(resultSet.getLong("user_id")).get();
        shoppingCard.setUser(user);
        shoppingCard.setId(resultSet.getLong("card_id"));
        return shoppingCard;
    }

    private List<Product> getProductsFromShoppingCard(Long id, Connection connection)
            throws SQLException {
        String queryProducts = "SELECT * FROM products JOIN shopping_cards_products "
                + "ON products.product_id = shopping_cards_products.product_id "
                + "WHERE shopping_cards_products.card_id = ?";
        PreparedStatement statement = connection.prepareStatement(queryProducts);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product(resultSet.getString("product_name"),
                    resultSet.getDouble("price"));
            product.setId(resultSet.getLong("product_id"));
            products.add(product);
        }
        return products;
    }
}
