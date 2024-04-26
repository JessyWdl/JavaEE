package com.jessy.entity.utlitaire;


import com.jessy.entity.dao.DAOClient;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.logging.Logger;

public class Password {


    public static final Logger LOGGER =
            Logger.getLogger(DAOClient.class.getName());

    /**
     * Extraction du sel
     *
     * @param encryptedPasword
     * @return
     */

    private static final String extractSalt(String encryptedPasword) {

        // echappement du dollar sinon regex
        String[] parts = encryptedPasword.split("\\$");

        // format algo$sel$motdepassehashé

        // si le split donne moins de 3 éléments alors problème
        if (parts.length < 3) {
            LOGGER.info("hash seems malformed [hash=" + encryptedPasword + "] [parts=" + Arrays.toString(parts) + "]");
        }
        // retourne le sel
        return parts[1];
    }

    /**
     * Méthode de hashage
     *
     * @param password
     * @return
     */
    private static final String encryptPassword(String password) {
        //  format algo$sel$motdepassehashé

        // création d'un sel de 30 caractéres
        String salt = Token.generateToken(30);

        // hashage motdepasse + sel
        return encryptPassword(password, salt);
    }

    /**
     * Méthode de hashage avec sel
     *
     * @param password
     * @param salt
     * @return
     */
    private static final String encryptPassword(String password, String salt) {
        // ALGO$SALT$HASH

        // hashage du mot de passe avec le sel et le poivre
        //  TODO  modifier pour aller chercher le poivre dans un fichier d'environnement
        String hash = hashWithSha256(password + salt + "PEPPER A FAIRE");

        // création du format ALGO$sel$hashage
        String result = String.format("sha256$%s$%s", salt, hash);

        return result;

    }
    /**
     * Méthode utilisant l'algo SHA256
     *
     * @param password
     * @return passwordHash
     */
    private static final String hashWithSha256(String password) {

        String hash = DigestUtils.sha256Hex(password);

        LOGGER.info("hash " + password + " to " + hash);

        return hash;

    }

    /**
     * Méthode de contrôle d'un hash
     *
     * @param encryptedPassword : le mot de passe déja hashé
     * @param password          : le mot de passe à contrôler
     * @return true ou false
     */
    private static final boolean verifyPassword(String encryptedPassword, String password) {

        // récupération du sel contenu dans le hash sauvegardé
        String salt = extractSalt(encryptedPassword);

        // contrôle de l'égalité avec le mot de passe saisie.
        return encryptedPassword.equals(encryptPassword(password, salt));

    }
}
