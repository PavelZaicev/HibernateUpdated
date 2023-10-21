package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = """ 
                CREATE TABLE users
                (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR NOT NULL,
                        lastName VARCHAR NOT NULL,
                        age INTEGER NOT NULL
                         )
                """;
        try (var connection = Util.connect();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sql = """
                DROP TABLE users
              """;
        try
                (var connection = Util.connect();
                 var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                INSERT INTO users(name, lastName, age)
                VALUES
                (?, ?, ?)
                """;
        try (var connection = Util.connect();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setInt(3, age);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = """
                DELETE FROM users
                WHERE id = ?""";
        try (var connection = Util.connect();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
       String sql = """
                SELECT *
                 FROM users
                """;
        try (var connection = Util.connect();
             var prepareStatement = connection.prepareStatement(sql)) {

            List<User> users = new ArrayList<>();
            var resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                users.add(new User(name, lastName, age));
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Возвращаем пустой список в случае исключения
        }
    }

    public void cleanUsersTable() {
        String sql = """
                TRUNCATE  users
                """;
        try (var connection = Util.connect();
             var prepareStatement = connection.prepareStatement(sql)) {
            var i = prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
