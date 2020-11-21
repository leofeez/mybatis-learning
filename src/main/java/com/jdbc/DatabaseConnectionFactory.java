package com.jdbc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnectionFactory {

    private static final Map<DatabaseType, DatabaseConnection> CONNECTION_POOL = new HashMap<>();

    static {
        CONNECTION_POOL.put(DatabaseType.MYSQL, new MySqlDatabaseConnectionImpl());
    }

    public static Connection getConnection(DatabaseType databaseType) {
        return CONNECTION_POOL.get(databaseType).getConnection();
    }
}
