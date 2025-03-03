package jdbc.dao;

import jdbc.entity.User;
import java.util.List;

public interface UserDao {
    void createUser(User user);
    List<User> selectAllUsers();
    User findUserById(int id);
    void updateUsers(int age, int id); //change the age of user by the ID
    void deleteUserById(int id);
}
