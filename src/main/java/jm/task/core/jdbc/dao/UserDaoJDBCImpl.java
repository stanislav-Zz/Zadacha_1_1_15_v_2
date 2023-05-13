package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id));";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("TABLE OK");
        } catch (SQLException e) {
            System.out.println("createUserTable EXCEPTION");
        }

    }
    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS users";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("TABLE DELETE");
        } catch (SQLException e) {
            System.out.println("dropUsersTable EXCEPTION");
        }

    }
    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?);";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

                System.out.println("user с именем - " + name + "добавлен в базу данных");

        } catch (SQLException e) {
            System.out.println("saveUser EXCEPTION");
        }

    }
    @Override
    public void removeUserById(long id) {

        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM users WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("user удалён");

        } catch (SQLException e) {
            System.out.println("removeUserById EXCEPTION");
        }

    }
    @Override
    public List<User> getAllUsers() {
        Statement statement = null;
        List<User> people = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastname"));
                user.setAge(result.getByte("age"));

                people.add(user);
            }
        } catch (SQLException e) {
            System.out.println("getAllUsers EXCEPTION");
        }
        return people;
    }
    @Override
    public void cleanUsersTable() {

        PreparedStatement preparedStatement = null;
        String sql = "TRUNCATE users";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("cleanUsersTable EXCEPTION");
        }

    }
}
