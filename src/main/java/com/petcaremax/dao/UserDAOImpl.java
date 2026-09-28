package com.petcaremax.dao;

import com.petcaremax.model.User;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findByUsername(
            String username
    ) throws Exception {

        String sql =
                "SELECT user_id, username, password_hash, "
                + "full_name, role, status "
                + "FROM users "
                + "WHERE username = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password_hash"),
                            resultSet.getString("full_name"),
                            resultSet.getString("role"),
                            resultSet.getString("status")
                    );
                }
            }
        }

        return null;
    }
}