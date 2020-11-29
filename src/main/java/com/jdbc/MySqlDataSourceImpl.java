package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDataSourceImpl implements DataSource {

    private static final Object LOCK = new Object();

    private static final String URL = "jdbc:mysql://localhost:3306/learning?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=UTC";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "admin123";

    private Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        if (connection == null) {
            synchronized (LOCK) {
                if (connection == null) {
                    connection = doGetConnection();
                }
            }
        }
        return connection;
    }

    @Override
    public void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Connection doGetConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
