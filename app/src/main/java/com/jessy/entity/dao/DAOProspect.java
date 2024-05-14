package com.jessy.entity.dao;

import com.jessy.entity.entites.Prospect;
import com.jessy.entity.exception.DaoException;
import com.jessy.entity.exception.MonException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

import static com.jessy.entity.logs.Logs.LOGGER;

/**
 * Cette classe permet d'accéder au données de la table Prospect qui se trouve en BDD
 */
public class DAOProspect {
    /**
     * Recupère tout les prospect depuis la table
     *
     * @return Une ArrayList contenant tout les objets Prospect
     * @throws Exception Si une erreur se produit dans la BDD
     */
    public static ArrayList<Prospect> findAll() throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "select * from prospect";
        ArrayList<Prospect> prospectArrayList = new ArrayList<>();
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Prospect prospect = new Prospect();
                prospect.setID(rs.getInt("ID"));
                prospect.setRaisonSociale(rs.getString("RaisonSociale"));
                prospect.setNumRue(rs.getString("NumRue"));
                prospect.setNomRue(rs.getString("NomRue"));
                prospect.setCodePostal(rs.getString("CodePostal"));
                prospect.setVille(rs.getString("Ville"));
                prospect.setTel(rs.getString("Tel"));
                prospect.setEmail(rs.getString("Email"));
                prospect.setDateProspect(rs.getDate("DateProspect").toLocalDate());
                prospect.setProspectInteresse(rs.getString("ProspectInteresse"));
                prospect.setCommentaire(rs.getString("Commentaire"));

                prospectArrayList.add(prospect);
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
        return prospectArrayList;
    }

    public static Prospect findByName(Object where) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "select * from prospect where RaisonSociale=?";
        Prospect prospect = new Prospect();
        try {
            stmt = con.prepareStatement(query);
            stmt.setObject(1, where);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                prospect.setID(rs.getInt("ID"));
                prospect.setRaisonSociale(rs.getString("RaisonSociale"));
                prospect.setNumRue(rs.getString("NumRue"));
                prospect.setNomRue(rs.getString("NomRue"));
                prospect.setCodePostal(rs.getString("CodePostal"));
                prospect.setVille(rs.getString("Ville"));
                prospect.setTel(rs.getString("Tel"));
                prospect.setEmail(rs.getString("Email"));
                prospect.setDateProspect(rs.getDate("DateProspect").toLocalDate());
                prospect.setProspectInteresse(rs.getString("ProspectInteresse"));
                prospect.setCommentaire(rs.getString("Commentaire"));
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
        return prospect;
    }

    public static void create(Prospect prospect) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "Insert into prospect (RaisonSociale,NumRue,NomRue,CodePostal,Ville," +
                "Tel,Email,DateProspect,ProspectInteresse," +
                "Commentaire) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, prospect.getRaisonSociale());
            stmt.setString(2, prospect.getNumRue());
            stmt.setString(3, prospect.getNomRue());
            stmt.setString(4, prospect.getCodePostal());
            stmt.setString(5, prospect.getVille());
            stmt.setString(6, prospect.getTel());
            stmt.setString(7, prospect.getEmail());
            stmt.setObject(8, prospect.getDateProspect());
            stmt.setString(9, prospect.getProspectInteresse());
            stmt.setString(10, prospect.getCommentaire());

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
    }

    public static Prospect update(int where, Prospect prospect) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "UPDATE prospect SET RaisonSociale = ?, NumRue = ?, NomRue = ?, CodePostal = ?," +
                "Ville = ?, Tel = ?, Email = ?, DateProspect = ?, ProspectInteresse = ?, Commentaire = ? " +
                "WHERE `ID` = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, prospect.getRaisonSociale());
            stmt.setString(2, prospect.getNumRue());
            stmt.setString(3, prospect.getNomRue());
            stmt.setString(4, prospect.getCodePostal());
            stmt.setString(5, prospect.getVille());
            stmt.setString(6, prospect.getTel());
            stmt.setString(7, prospect.getEmail());
            stmt.setDate(8, Date.valueOf(prospect.getDateProspect()));
            stmt.setString(9, prospect.getProspectInteresse());
            stmt.setString(10, prospect.getCommentaire());
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
        return prospect;
    }

    public static void delete(int where) throws Exception {
        Connection con = DatabaseConnection.con();
        PreparedStatement stmt = null;
        String query = "Delete from prospect where ID=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, where);

            stmt.executeUpdate();
        }catch (SQLException sqle) {
            LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
