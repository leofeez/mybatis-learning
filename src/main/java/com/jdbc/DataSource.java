package com.jdbc;

import java.sql.Connection;

public interface DataSource {

    Connection getConnection();

    void close(Connection connection);

}
