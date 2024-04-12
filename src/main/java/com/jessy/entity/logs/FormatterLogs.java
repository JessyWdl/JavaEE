package com.jessy.entity.logs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FormatterLogs extends Formatter {
    public String format(LogRecord record) {
        // TODO Auto-generated method stub
        //Date récup' ici
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder result = new StringBuilder();

        result.append(format.format(new Date()));
        result.append(" Level : " );
        result.append(record.getLevel());

        result.append(" / Message : ");
        result.append(record.getMessage());

        result.append(" / Méthode : ");
        result.append(record.getSourceMethodName());

        result.append(" / Classe : ");
        result.append(record.getSourceClassName());
        //Construction du message de log

        result.append("\n");
        return result.toString();
    }
}

