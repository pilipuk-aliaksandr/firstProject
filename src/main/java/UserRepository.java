import java.sql.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String INSERT_QUERY = """
                INSERT INTO users (name, surname, age, username, password, inserted_data_at_utc, updated_data_at_utc)
                VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
    private static final String FIND_BY_USER_QUERY = """
                SELECT * FROM users WHERE name=?
        """;
    private static final String UPDATE_QUERY = """
                UPDATE users
                SET age = age - ?
        """;
    private static final String DELETE_QUERY = """
                DELETE FROM users WHERE id=?
        """;
    private static final String SELECT_ALL_QUERY = """
                SELECT * FROM users
        """;

    public void insertToUsersDB(User user) throws SQLException {
        try(
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
                ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setTimestamp(6, new Timestamp(user.getInserted_data_at_utc().toInstant(ZoneOffset.UTC).toEpochMilli()));
            preparedStatement.setTimestamp(7, new Timestamp(user.getUpdated_data_at_utc().toInstant(ZoneOffset.UTC).toEpochMilli()));

            preparedStatement.execute();
        }
    }

    public List<User> findUserByNameFromUsersDB(String name) throws SQLException {
        try(
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_QUERY);
        ) {
            preparedStatement.setString(1,name);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                List<User> users = new ArrayList<>();
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
            return users;
            }
        }
    }

    public void deleteUserByIdFromUsersDB(int ID) throws SQLException {
        try(
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        ) {
            preparedStatement.setInt(1,ID);
            preparedStatement.execute();
        }
    }

    public String updateUserAgeInUsersDB(int parameterToUpdateAge) throws SQLException {
        try(
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            preparedStatement.setInt(1,parameterToUpdateAge);
            if(preparedStatement.executeUpdate() > 0) {
                return "Update successfully";
            } else {
                return "Update error";
            }
        }
    }

    public List<User> selectAllUsersFromUsersDB() throws SQLException {
        try(
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
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
                return users;
        }
    }
}