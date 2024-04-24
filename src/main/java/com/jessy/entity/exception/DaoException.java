package com.jessy.entity.exception;

import java.util.logging.Level;

/**
 * Cette classe represente une exception custom utilisé principalement pour les DAO
 * elle extend la classe Exception pour permettre la création de message custom.
 */
public class DaoException extends Exception {
    private Level Gravite;

    /**
     * Construction de DaoException avec un message et level spécifié
     *
     * @param Message Le message d'erreur décrivant l'exception.
     * @param Gravite Le degrés de sévérité de l'erreur.
     */
    public DaoException(String Message, Level Gravite) {
        super(Message);
        setGravite(Gravite);
    }
    /**
     * Permet de définir le degrés de sévérité de l'exception en fonction du level
     */
    public void setGravite(Level Gravite) {
        this.Gravite = Gravite;
    }

    /**
     * Récupère le level de l'exception
     */
    public Level getGravite() {
        return Gravite;
    }
}

