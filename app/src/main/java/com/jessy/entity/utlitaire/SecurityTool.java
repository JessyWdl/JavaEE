package com.jessy.entity.utlitaire;

import com.jessy.entity.dao.DAOClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;

public class SecurityTool {


    public static final Logger LOGGER =
            Logger.getLogger(DAOClient.class.getName());

    public static String getPepper(){
        Properties prop = new Properties();
        InputStreamReader input = null;
        try {
            input = new InputStreamReader(SecurityTool.class.getClassLoader().getResourceAsStream("pepper.properties"));

            // load a properties file
            prop.load(input);

        } catch (NullPointerException | IOException ex) {
            LOGGER.info(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    LOGGER.info(e.getMessage());
                }
            }
        }
        return prop.getProperty("pepper");
    }

    //--------------------- CONSTRUCTORS ---------------------------------------
    private SecurityTool() {
        throw new UnsupportedOperationException(
                "This class isn't meant to be instantiate");
    }
}
