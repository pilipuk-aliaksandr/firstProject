package jdbcTest.entityTest;

import jdbc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void prepareTest() {
        user = new User();
    }

    @Test
    void testGetId() {
        assertEquals(0, user.getId());
    }

    @Test
    void testSetId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    void getName() {
        assertEquals(null, user.getName());
    }

    @Test
    void setName() {
        user.setName("Sasha");
        assertEquals("Sasha", user.getName());
    }

    @Test
    void getSurname() {
        assertEquals(null, user.getSurname());
    }

    @Test
    void setSurname() {
        user.setSurname("Pilipuk");
        assertEquals("Pilipuk", user.getSurname());
    }

    @Test
    void getAge() {
        assertEquals(0, user.getAge());
    }

    @Test
    void setAge() {
        user.setAge(24);
        assertEquals(24, user.getAge());
    }

    @Test
    void getUsername() {
        assertEquals(null, user.getUsername());
    }

    @Test
    void setUsername() {
        user.setUsername("Kaledir");
        assertEquals("Kaledir", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals(null, user.getPassword());
    }

    @Test
    void setPassword() {
        user.setPassword("Qwerty");
        assertEquals("Qwerty", user.getPassword());
    }

    @Test
    void getInserted_data_at_utc() {
        assertEquals(null, user.getInserted_data_at_utc());
    }

    @Test
    void setInserted_data_at_utc() {
        user.setInserted_data_at_utc(LocalDateTime.now());
        assertEquals(LocalDateTime.now(), user.getInserted_data_at_utc());
    }

    @Test
    void getUpdated_data_at_utc() {
        assertEquals(null, user.getUpdated_data_at_utc());
    }

    @Test
    void setUpdated_data_at_utc() {
        user.setUpdated_data_at_utc(LocalDateTime.of(2025, 2, 5, 18, 0, 0));
        assertEquals(LocalDateTime.of(2025, 2, 5, 18, 0, 0), user.getUpdated_data_at_utc());
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
        assertEquals("jdbc.DAO.User{id=0, name='null', surname='null', age=0, username='null', password='null', inserted_data_at_utc=null, updated_data_at_utc=null}", user.toString());
    }
}