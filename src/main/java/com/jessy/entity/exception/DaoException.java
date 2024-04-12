package com.jessy.entity.exception;

public class DaoException extends Exception{
    public DaoException(String Message, int Gravite) {
        super(Message);
        if (Gravite > 5){
            System.exit(1);
        }
    }
}
