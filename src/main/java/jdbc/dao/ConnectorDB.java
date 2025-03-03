package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection () throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        final String URL = resource.getString("db.url");
        final String USERNAME = resource.getString("db.username");
        final String PASSWORD = resource.getString("db.password");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}