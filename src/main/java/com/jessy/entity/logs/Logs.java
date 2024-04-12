package com.jessy.entity.logs;
import java.util.logging.Logger;

public class Logs {

    public static final Logger LOGGER =
            //Appel de la methode logger, permet de récup' un fichier .log
            Logger.getLogger(Logs.class.getName());
}

