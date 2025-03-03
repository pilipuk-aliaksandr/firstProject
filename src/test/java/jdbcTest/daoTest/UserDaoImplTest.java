package jdbcTest.daoTest;

import jdbc.dao.UserDaoImpl;
import jdbc.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    @BeforeEach
    static void setUpAndCleanDB() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        final String URL = resource.getString("db.url");
        final String USERNAME = resource.getString("db.username");
        final String PASSWORD = resource.getString("db.password");
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        try (Statement query = conn.createStatement()) {
            query.execute("""
                    DROP TABLE IF EXISTS users
                    CREATE TABLE users(id INT8 PRIMARY KEY autoincrement=true,
                    name VARCHAR(264),
                    surname VARCHAR(264),
                    age INT4,
                    username VARCHAR(16),
                    password VARCHAR(264),
                    inserted_data_at_utc TIMESTAMP,
                    updated_data_at_utc TIMESTAMP)
                    """);
        }
    }

    @Test
    void createUser() {
        User user = new User();
        user.setId(1);
        user.setName("Sasha");
        user.setSurname("Pilipuk");
        user.setUsername("Kaledir");
        user.setPassword("Vishne");
        user.setAge(1);
        user.setInserted_data_at_utc(LocalDateTime.of(2025, 2, 5, 18, 0, 0));
        user.setUpdated_data_at_utc(LocalDateTime.of(2025, 2, 5, 18, 0, 0));
        userDaoImpl.createUser(user);
        assertEquals(user, userDaoImpl.findUserById(1));
    }

    @Test
    void selectAllUsers() {
        userDaoImpl.selectAllUsers();
    }

    @Test
    void findUserById() {
    }

    @Test
    void updateUsers() {
    }

    @Test
    void deleteUserById() {
    }
}