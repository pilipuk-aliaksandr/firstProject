package jdbc.dao;

import jdbc.entity.User;

import java.sql.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String CREATE_QUERY = """
                    INSERT INTO users (name, surname, age, username, password, inserted_data_at_utc, updated_data_at_utc)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String FIND_BY_ID_QUERY = """
                    SELECT * FROM users WHERE id=?
            """;
    private static final String UPDATE_QUERY = """
                    UPDATE users SET age=? WHERE id=?
            """;
    private static final String DELETE_QUERY = """
                    DELETE FROM users WHERE id=?
            """;
    private static final String SELECT_ALL_QUERY = """
                    SELECT * FROM users
            """;

    @Override
    public void createUser(User user) {
        try (
                PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(CREATE_QUERY);
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setTimestamp(6, new Timestamp(user.getInserted_data_at_utc().toInstant(ZoneOffset.UTC).toEpochMilli()));
            preparedStatement.setTimestamp(7, new Timestamp(user.getUpdated_data_at_utc().toInstant(ZoneOffset.UTC).toEpochMilli()));

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SELECT_ALL_QUERY);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setUsername(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setInserted_data_at_utc(resultSet.getTimestamp(7).toLocalDateTime());
                user.setUpdated_data_at_utc(resultSet.getTimestamp(8).toLocalDateTime());

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User findUserById(int id) {
        User user = new User();
        try (
                PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(FIND_BY_ID_QUERY);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setUsername(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setInserted_data_at_utc(resultSet.getTimestamp(7).toLocalDateTime());
                user.setUpdated_data_at_utc(resultSet.getTimestamp(8).toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateUsers(int age, int id) {
        User user = new User();
        try (
                PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(UPDATE_QUERY);
        ) {
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserById(int id) {
        try (
                PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(DELETE_QUERY);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
