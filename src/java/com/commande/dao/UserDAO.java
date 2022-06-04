package com.commande.dao;

import java.sql.*;

import com.commande.model.UserModel;

public class UserDAO extends DAOContext {

    public static UserModel isValidLogin(String login, String password) {
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            String strSql = "SELECT * FROM User WHERE user = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                     if (resultSet.next()) {
                        return new UserModel(resultSet.getInt("idUser"),
                                resultSet.getString("user"),
                                resultSet.getString("password"));
                    } else {
                        return null;
                    }                  
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
