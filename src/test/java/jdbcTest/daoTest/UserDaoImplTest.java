package jdbcTest.daoTest;

import jdbc.dao.UserDaoImpl;
import jdbc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    @BeforeEach
    void setUpAndCleanDB() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        final String URL = resource.getString("db.url");
        final String USERNAME = resource.getString("db.username");
        final String PASSWORD = resource.getString("db.password");
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        try (Statement query = conn.createStatement()) {
            query.execute("TRUNCATE TABLE users");
        }
    }

    @Test
    void createUser() {
        User user = new User();
        user.setId(4);
        user.setName("Sasha");
        user.setSurname("Pilipuk");
        user.setUsername("Kaledir");
        user.setPassword("Vishne");
        user.setAge(1);
        userDaoImpl.createUser(user);
        assertEquals(user, userDaoImpl.findUserById(4));
    }

    @Test
    void selectAllUsers() {
    }

    @Test
    void findUserById() {
        User user1 = new User();
        user1.setId(16);
        user1.setName("Sasha");
        user1.setSurname("Pilipuk");
        user1.setUsername("Kaledir");
        user1.setPassword("Vishne");
        user1.setAge(1);
        userDaoImpl.createUser(user1);

        User user2 = new User();
        user2.setId(17);
        user2.setName("Veronica");
        user2.setSurname("Piun");
        user2.setUsername("Piunchik");
        user2.setPassword("Vish");
        user2.setAge(2);
        userDaoImpl.createUser(user2);

        assertEquals(user2, userDaoImpl.findUserById(17));
    }

    @Test
    void updateUsers() {
        User user1 = new User();
        user1.setId(21);
        user1.setName("Sasha");
        user1.setSurname("Pilipuk");
        user1.setUsername("Kaledir");
        user1.setPassword("Vishne");
        user1.setAge(1);
        userDaoImpl.createUser(user1);
        userDaoImpl.updateUsers(24, 21);
        user1.setAge(24);
        assertEquals(user1, userDaoImpl.findUserById(21));
    }

    @Test
    void deleteUserById() {
        User user1 = new User();
        user1.setId(18);
        user1.setName("Sasha");
        user1.setSurname("Pilipuk");
        user1.setUsername("Kaledir");
        user1.setPassword("Vishne");
        user1.setAge(1);
        userDaoImpl.createUser(user1);

        User user2 = new User();
        user2.setId(19);
        user2.setName("Veronica");
        user2.setSurname("Piun");
        user2.setUsername("Piunchik");
        user2.setPassword("Vish");
        user2.setAge(2);
        userDaoImpl.createUser(user2);

        List<User> listUsers = new ArrayList<>();
        listUsers.add(user1);
        listUsers.add(user2);
        listUsers.remove(user2);

        List<User> listFromDB = userDaoImpl.selectAllUsers();
        listFromDB.remove(1);

        assertEquals(listFromDB, listUsers);
    }
}