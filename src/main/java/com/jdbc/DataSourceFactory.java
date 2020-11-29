package com.jdbc;

import java.util.HashMap;
import java.util.Map;

public class DataSourceFactory {

    private static final Map<DatabaseType, DataSource> DATA_SOURCE = new HashMap<>();

    static {
        DATA_SOURCE.put(DatabaseType.MYSQL, new MySqlDataSourceImpl());
    }

    public static DataSource getDataSource(DatabaseType databaseType) {
        return DATA_SOURCE.get(databaseType);
    }
}
