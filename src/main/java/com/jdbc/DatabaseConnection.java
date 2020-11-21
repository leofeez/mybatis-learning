package com.jdbc;

import java.sql.Connection;

public interface DatabaseConnection {

    Connection getConnection();

    void close();

}
