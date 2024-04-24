package com.jessy.entity.logs;
import java.util.logging.Logger;

/**
 * Cette classe permet d'appeler une instance du nom de LOGGER afin de l'utiliser
 * pour alimenter les logs du programme
 */
public class Logs {

    /**
     * L'instance LOGGER qui permet de créer les messages dans le fichier logs
     */
    public static final Logger LOGGER =
            //Appel de la methode logger, permet de récup' un fichier .log
            Logger.getLogger(Logs.class.getName());
}

