package mate.academy.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.shop.dao.ProductDao;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.Product;
import mate.academy.shop.util.ConnectionUtil;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products (product_name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            String[] generatedColumns = {"product_id"};
            PreparedStatement statement = connection.prepareStatement(query, generatedColumns);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can not create product", e);
        }
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = getProductDetails(resultSet);
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("No such product in DB!", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = getProductDetails(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("No such product in DB!", e);
        }
    }

    @Override
    public Product update(Product obj) {
        String query = "UPDATE products SET product_name = ?, price = ? WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, obj.getName());
            statement.setDouble(2, obj.getPrice());
            statement.setLong(3, obj.getId());
            statement.execute();
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException("No such product in DB!", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("No such product in DB!", e);
        }
    }

    Product getProductDetails(ResultSet resultSet) throws SQLException {
        Product product = new Product(resultSet.getString("product_name"),
                resultSet.getDouble("price"));
        product.setId(resultSet.getLong("product_id"));
        return product;
    }
}
