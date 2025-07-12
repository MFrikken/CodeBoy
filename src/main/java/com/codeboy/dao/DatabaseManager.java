package com.codeboy.dao;

import java.sql.*;
import java.util.logging.Logger;

abstract class DatabaseManager {
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());

    private static DatabaseManager instance;

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:sage_java;DB_CLOSE_DELAY=-1";

    // TODO: provide credentials in secure environment
    private static final String USER = "sage";
    private static final String PASSWORD = "java";

    private static Connection connection;

    public static Connection establishConnection() {
        if (connection != null)
            return connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.severe("[DatabaseManager] Error while trying to connect to database: " + e.getMessage());
        }
        return null;
    }

    public static boolean closeConnection() {
        if (connection == null)
            return true;
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.severe("[DatabaseManager] Error while trying to close database connection: " + e.getMessage());
        }
        return true;
    }
}
