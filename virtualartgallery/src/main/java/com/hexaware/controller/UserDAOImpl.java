package com.hexaware.controller;

import com.hexaware.model.User;

import java.sql.*;


public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public User getUserById(int userID) {
        String sql = "SELECT * FROM User WHERE UserID=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserID(resultSet.getInt("UserID"));

        return user;
    }

    @Override
    public void close() {
       
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
