package com.sage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

abstract class DatabaseManager {
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());

    private static final String JDBC_DRIVER = "org.h2.Driver"; 
    private static final String DB_URL = "jdbc:h2:mem:sage_java";

    // TODO: provide credentials in secure environment
    private static final String USER = "sage";
    private static final String PASSWORD = "java";

    private static Connection connection;

    public static Connection establishConnection() {
        if (connection != null)
            return connection;
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
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

    public static boolean initializeDatabase() {
        try (Connection connection = establishConnection()) {
            Statement stmt = connection.createStatement();
            stmt.execute("RUNSCRIPT FROM 'classpath:init.sql'");
            return true;
        } catch (SQLException e) {
            LOGGER.severe("[DatabaseManager] Error while initializing database: " + e.getMessage());
        }
        return false;
    }
}
