package mate.academy.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.shop.dao.UserDao;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.Role;
import mate.academy.shop.model.User;
import mate.academy.shop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public Optional<User> getByLogin(String login) {
        String query = "SELECT * FROM users WHERE username LIKE ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserDetails(resultSet);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("An error with getting user from DB!", e);
        }
    }

    @Override
    public User create(User obj) {
        String query = "INSERT INTO users (username, password, salt) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            String[] generatedColumns = {"user_id"};
            PreparedStatement statement = connection.prepareStatement(query, generatedColumns);
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getPassword());
            statement.setBytes(3, obj.getSalt());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                obj.setId(resultSet.getLong(1));
            }
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException("An error to create user!", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserDetails(resultSet);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("An error while getting user from DB!", e);
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        try (Connection connection = ConnectionUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUserDetails(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("An error while getting all users from DB!", e);
        }
    }

    @Override
    public User update(User obj) {
        String query = "UPDATE users SET username = ?, password = ? WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getPassword());
            statement.setLong(3, obj.getId());
            statement.execute();
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException("An error while updating the user from db", e);
        }
    }

    @Override
    public List<Role.RoleName> getUserRolesById(Long userId) {
        String query = "SELECT * FROM users JOIN users_roles "
                + "ON users.user_id = users_roles.user_id JOIN roles "
                + "ON users_roles.role_id = roles.role_id WHERE users.user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Role.RoleName> roles = new ArrayList<>();
            while (resultSet.next()) {
                String strRole = resultSet.getString("role_name");
                for (Role.RoleName role : Role.RoleName.values()) {
                    if (role.name().equals(strRole)) {
                        roles.add(role);
                    }
                }
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user roles", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("An error while deleting user from DB!", e);
        }
    }

    public User getUserDetails(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setSalt(resultSet.getBytes("salt"));
        return user;
    }
}
