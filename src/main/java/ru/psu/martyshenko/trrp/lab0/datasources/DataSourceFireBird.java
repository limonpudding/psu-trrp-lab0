package ru.psu.martyshenko.trrp.lab0.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFireBird {

    private static final String url = "jdbc:firebirdsql:local:/home/kepouch/test.fdb?charSet=utf8";
    private static final String user = "sysdba";
    private static final String password = "masterkey";

    private static DataSourceFireBird instance;
    private Connection connection;

    private DataSourceFireBird(Connection connection) {
        this.connection = connection;
    }

    public static DataSourceFireBird getInstance() {
        if (instance == null) {
            try {
                DriverManager.registerDriver(new org.firebirdsql.jdbc.FBDriver());
                Connection connection = DriverManager.getConnection(url, user, password);
                instance = new DataSourceFireBird(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
