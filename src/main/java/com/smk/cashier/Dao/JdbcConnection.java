package com.smk.cashier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcConnection {
    private static Optional<Connection> connection = Optional.empty();

    public static Optional<Connection> getConnection() {
        if(connection.isEmpty()){
            String url = "jdbc:postgresql://localhost:5432/cashier";
            String user = "cashier";
            String password = "c45h13r456";
            try {
                Class.forName("org.postgresql.Driver");
                connection = Optional.ofNullable(DriverManager.getConnection(url, user, password));
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}