package com.jessy.entity.dao;

import com.jessy.entity.exception.DaoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.jessy.entity.logs.Logs.LOGGER;

public class DatabaseConnection {
    public static Connection connection = null;
    private DatabaseConnection() throws Exception {
        try {
            final Properties dataProperties = new Properties();

            File fichier = new File("database.properties");
            FileInputStream input = new FileInputStream(fichier);
            dataProperties.load(input);

            connection = DriverManager.getConnection(
                    dataProperties.getProperty("url"),
                    dataProperties.getProperty("login"),
                    dataProperties.getProperty("password")
            );
            System.out.println("Database initialised");
        } catch (SQLException e) {
            throw new DaoException("Username or password incorrect",1);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
    //Singleton (vérifier que la connection est bien fermé avant d'en ouvrir une nouvelle)
    public static Connection con() throws Exception {
        if (connection == null){
            System.out.println("connection established");
            new DatabaseConnection();
        }
        return connection;
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (connection != null) {
                    try {
                        LOGGER.info("Database closed");
                        connection.close();
                    } catch (SQLException ex) {
                        LOGGER.severe("problème database " + ex.getMessage());
                    }
                }
            })
        );
    }
}
