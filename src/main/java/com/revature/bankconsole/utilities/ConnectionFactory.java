package com.revature.bankconsole.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    // The next three blocks make it a singleton
    private static ConnectionFactory connFactory = new ConnectionFactory();

    private Properties props = new Properties();

    private ConnectionFactory() {

        try {
            props.load(new FileReader("./src/main/resources/application.properties"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        return connFactory;
    }

    public Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection
                    (props.getProperty("url"),
                            props.getProperty("username"),
                            props.getProperty("password"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            throw new RuntimeException("Failed to establish connection");
        }
        return conn;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}