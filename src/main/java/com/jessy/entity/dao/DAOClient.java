package com.jessy.entity.dao;

import com.jessy.entity.entites.Client;
import com.jessy.entity.exception.MonException;
import java.sql.*;
import java.util.ArrayList;
import static com.jessy.entity.logs.Logs.LOGGER;

public class DAOClient {
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
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        }finally {
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
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        } catch (MonException e) {
            throw new RuntimeException(e);
        } finally {
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
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        } finally {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        } catch (SQLException e) {
            LOGGER.info("Database closed");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
