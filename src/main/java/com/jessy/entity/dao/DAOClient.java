package com.jessy.entity.dao;

import com.jessy.entity.entites.Client;
import com.jessy.entity.exception.DaoException;
import com.jessy.entity.exception.MonException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

import static com.jessy.entity.logs.Logs.LOGGER;

/**
 * This class provides data access methods for interacting with the Client table in the database.
 */
public class DAOClient {
    /**
     * Recupère tout les clients depuis la table
     *
     * @return Une ArrayList contenant tout les objets Client
     * @throws Exception Si une erreur se produit dans la BDD
     */
    public static ArrayList<Client> findAll() throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "select * from client";
        ArrayList<Client> clientArrayList = new ArrayList<>();
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setID(rs.getInt("ID"));
                client.setRaisonSociale(rs.getString("RaisonSociale"));
                client.setNumRue(rs.getString("NumRue"));
                client.setNomRue(rs.getString("NomRue"));
                client.setCodePostal(rs.getString("CodePostal"));
                client.setVille(rs.getString("Ville"));
                client.setTel(rs.getString("Tel"));
                client.setEmail(rs.getString("Email"));
                client.setChiffreAffaire(rs.getDouble("ChiffreAffaire"));
                client.setNbEmployes(rs.getInt("NbEmployes"));
                client.setCommentaire(rs.getString("Commentaire"));

                clientArrayList.add(client);
            }
        } catch (MonException me) {
            throw new DaoException(me.getMessage(), Level.WARNING);
        } catch (SQLException sqle) {
            LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return clientArrayList;
    }

    public static Client findByName(Object where) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "select * from client where RaisonSociale=?";
        Client client = new Client();
        try {
            stmt = con.prepareStatement(query);
            stmt.setObject(1, where);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                client.setID(rs.getInt("ID"));
                client.setRaisonSociale(rs.getString("RaisonSociale"));
                client.setNumRue(rs.getString("NumRue"));
                client.setNomRue(rs.getString("NomRue"));
                client.setCodePostal(rs.getString("CodePostal"));
                client.setVille(rs.getString("Ville"));
                client.setTel(rs.getString("Tel"));
                client.setEmail(rs.getString("Email"));
                client.setChiffreAffaire(rs.getDouble("ChiffreAffaire"));
                client.setNbEmployes(rs.getInt("NbEmployes"));
                client.setCommentaire(rs.getString("Commentaire"));
            }
        } catch (MonException me) {
            throw new DaoException(me.getMessage(), Level.WARNING);
        } catch (SQLException sqle) {
            if (sqle.getErrorCode() == 1062){
                throw new DaoException("La raison sociale doit être unique", Level.WARNING);
            } else if (sqle.getErrorCode() == 1406) {
                throw new DaoException("Trop de caractères", Level.WARNING);
            }
            LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        }  finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return client;
    }

    public static Client create(Client client) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "Insert into client (RaisonSociale,NumRue,NomRue,CodePostal," +
                "Ville,Tel,Email,ChiffreAffaire,NbEmployes,Commentaire) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, client.getRaisonSociale());
            stmt.setString(2, client.getNumRue());
            stmt.setString(3, client.getNomRue());
            stmt.setString(4, client.getCodePostal());
            stmt.setString(5, client.getVille());
            stmt.setString(6, client.getTel());
            stmt.setString(7, client.getEmail());
            stmt.setDouble(8, client.getChiffreAffaire());
            stmt.setInt(9, client.getNbEmployes());
            stmt.setString(10, client.getCommentaire());

            stmt.executeUpdate();
        }catch (SQLException sqle) {
            if (sqle.getErrorCode() == 1062){
                throw new DaoException("La raison sociale doit être unique", Level.WARNING);
            } else if (sqle.getErrorCode() == 1406) {
                throw new DaoException("Trop de caractères", Level.WARNING);
            }
            LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        }finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return client;
    }

    public static Client update(int where, Client client) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;

        String query = "UPDATE client SET RaisonSociale = ?, NumRue = ?, NomRue = ?, CodePostal = ?," +
                "Ville = ?, Tel = ?, Email = ?, ChiffreAffaire = ?, NbEmployes = ?, Commentaire= ? " +
                "WHERE ID = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, client.getRaisonSociale());
            stmt.setString(2, client.getNumRue());
            stmt.setString(3, client.getNomRue());
            stmt.setString(4, client.getCodePostal());
            stmt.setString(5, client.getVille());
            stmt.setString(6, client.getTel());
            stmt.setString(7,client.getEmail());
            stmt.setDouble(8,client.getChiffreAffaire());
            stmt.setInt(9, client.getNbEmployes());
            stmt.setString(10, client.getCommentaire());
            stmt.setInt(11, where);

            stmt.executeUpdate();
        }catch (SQLException sqle) {
            if (sqle.getErrorCode() == 1062){
                throw new DaoException("La raison sociale doit être unique", Level.WARNING);
            } else if (sqle.getErrorCode() == 1406) {
                throw new DaoException("Trop de caractères", Level.WARNING);
            }
            LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        }finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return client;
    }

    public static void delete(int where) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "Delete from client where ID=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, where);

            stmt.executeUpdate();
        } catch (SQLException sqle) {
            LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
