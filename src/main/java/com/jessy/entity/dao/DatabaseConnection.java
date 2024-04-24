package com.jessy.entity.dao;

import com.jessy.entity.exception.DaoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import static com.jessy.entity.logs.Logs.LOGGER;

/**
 * Cette classe permet la gestion de la BDD dans l'application
 * elle apporte des méthodes qui permette d'établir ou fermer la connection
 */
public class DatabaseConnection {
    /**
     * Une instance de connection static utilisé pour toute opération sur la BDD
     */
    public static Connection connection = null;
    /**
     * Construire une nouvelle instance de DatabaseConnection et établire la connection
     *
     * @throws Exception Si une erreur se produit au moment ou la connection s'établit
     */
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
        } catch (SQLException e) {
            throw new DaoException("Username or password incorrect", Level.INFO);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
    /**
     * Returns l'instance de connections à la bdd
     * Si la connection n'est pas déjà établis, en ouvre une nouvelle
     *
     * @throws Exception Si une erreur apparait lors de l'instanciation de la connection
     */
    //Singleton (vérifier que la connection est bien fermé avant d'en ouvrir une nouvelle)
    public static Connection con() throws Exception {
        if (connection == null){
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
                        LOGGER.severe("problème BDD " + ex.getMessage());
                    }
                }
            })
        );
    }
}
