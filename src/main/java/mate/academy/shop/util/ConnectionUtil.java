package mate.academy.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", ""); // your db username
        dbProperties.put("password", ""); // your db user`s password
        String url = "jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC";

        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException throwables) {
            throw new RuntimeException("Can't establish the connection to DB", throwables);
        }
    }
}
