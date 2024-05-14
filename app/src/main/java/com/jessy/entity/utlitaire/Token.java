package com.jessy.entity.utlitaire;

import java.security.SecureRandom;
import java.util.Random;

public class Token {


    /**
     * CSRF_TOKEN_VALUE_NAME.
     */
    public static final String CSRF_TOKEN_VALUE_NAME = "_csrfToken";
    /**
     * CSRF_TOKEN_LENGTH.
     */
    public static final int CSRF_TOKEN_LENGTH = 100;

    private Token() {
        throw new UnsupportedOperationException(
                "This class is not meant to be instanciated");
    }

    /**
     * méthode générant des token d'une longueur passée en paramètre.
     *
     * @param length : longueur du token
     * @return String : le token
     */
    public static String generateToken(final int length) {
        Random rng = new SecureRandom();
        // variable contenant les caractères autorisés pour la génération
        String charPool = "0123456789"
                + "abcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // variable de construction
        StringBuilder builder = new StringBuilder();
        // boucle de construction
        for (int i = 0; i < length; i++) {
            // génération d'un nombre aléatoire
            int randIndex = rng.nextInt(charPool.length());
            // ajout du caractere à l'emplacement
            builder.append(charPool.charAt(randIndex));
        }
        // retourne le token
        return builder.toString();
    }
    /**
     * méthode renvoyant un token de 100 caractères. (CSRF_TOKEN_LENGTH = 100)
     * @return String : token
     */
    public static String generateCsrfToken() {
        return generateToken(CSRF_TOKEN_LENGTH);
    }
}
