package com.jessy.entity.utlitaire;

import jakarta.servlet.http.Cookie;


public class Cookies {

    /**
     * AUTH_TOKEN_LIFESPAN.
     * 7 days, in seconds
     */
    public static final int AUTH_TOKEN_LIFESPAN = 7 * 24 * 60 * 60;

    /**
     * CookieHelper().
     */
    private Cookies() {
        throw new UnsupportedOperationException(
                "This class is not meant to be instanciated");
    }

    /**
     * méthode renvoyant un token de 50 caractères.
     *
     * @return String : token
     */
    public static String generateToken() {
        return Token.generateToken(50);
    }

    /**
     * méthode créant un cookie valide 7 jours s'appelant auth-token et.
     * contenant un token d'authentification
     *
     * @param token
     * @return Cookie : le cookie d'authentification
     */
    public static Cookie createAuthTokenCookie(final String token) {
        Cookie authTokenCookie = new Cookie("auth-token", token);
        // option pour la sécurité
        // Faille XSS pour empêcher que le cookie soit accéssible via Javascript
        authTokenCookie.setHttpOnly(true);
        // positionnement de la durée du cookie
        authTokenCookie.setMaxAge(AUTH_TOKEN_LIFESPAN);

        return authTokenCookie;
    }

    /**
     * suppression du token d'authentification.
     *
     * @return Cookie
     */
    public static Cookie createAuthTokenCookieDeletion() {
        Cookie authTokenCookie = new Cookie("auth-token", null);
        authTokenCookie.setMaxAge(0);

        return authTokenCookie;
    }

}
