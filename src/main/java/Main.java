import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setName("Sasha");
        user1.setSurname("Pili");
        user1.setAge(99);
        user1.setUsername("Kaledir");
        user1.setPassword("Vishnya9");
        user1.setInserted_data_at_utc(LocalDateTime.now());
        user1.setUpdated_data_at_utc(LocalDateTime.now());
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setName("Veronichka");
        user2.setSurname("Piunidze");
        user2.setAge(2);
        user2.setUsername("Veronichka");
        user2.setPassword("fkjsbd");
        user2.setInserted_data_at_utc(LocalDateTime.now());
        user2.setUpdated_data_at_utc(LocalDateTime.now());
        users.add(user2);

        UserRepository userRepository = new UserRepository();
        userRepository.insertToUsersDB(user2);

        List<User> foundUsersFromUsersDB = new ArrayList<>();
        System.out.println(foundUsersFromUsersDB = userRepository.findUserByNameFromUsersDB("Sasha"));

        userRepository.deleteUserByIdFromUsersDB(4);

        System.out.println(userRepository.updateUserAgeInUsersDB(10));

        System.out.println(userRepository.selectAllUsersFromUsersDB());
    }
}
